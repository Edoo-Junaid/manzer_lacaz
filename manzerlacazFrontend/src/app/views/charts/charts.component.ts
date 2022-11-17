import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

import {MenuCreation} from "./MenuCreation";
import {Router} from "@angular/router";
// @ts-ignore
import {MenuService} from "../../services/menu/menu.service";
import {Menu} from "../dashboard/Menu";
import {GetMenuList} from "../dashboard/GetMenuList";
import value from "*.json";

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.scss']
})
export class ChartsComponent implements OnInit {
  public modalVisibility = false;
  formData!: FormGroup;
  menu: Menu[] = [];
  public dayArray: string[] = new Array("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
  private existingMenuDescription = ['menuMon', 'menuTue', 'menuWed', 'menuThu', 'menuFri'];
  private existingPrice=['priceMon','priceTue','priceWed','priceThu','priceFri']
  private existingVeg=['optionMonVeg','optionTueVeg','optionWedVeg','optionThuVeg','optionFriVeg']
  private existingNonVeg=['optionMonNonVeg','optionTueNonVeg','optionWedNonVeg','optionThuNonVeg','optionFriNonVeg']
  menuDescriptions!: string[];
  priceDescriptions!: string[];

  // alerts
  dismissible: any;
  visibleMenuSave: any;
  visibleMenuNotSave: any;

  constructor(public menuService: MenuService, private _router: Router) {
  }

  ngOnInit(): void {

    if (localStorage.getItem('role') != 'Admin') this.modalVisibility = true;

    //Form variables
    this.formData = new FormGroup({
      //Number area-input week number
      weekNo: new FormControl(),

      //Text area- input menus
      menuMon: new FormControl(),
      menuTue: new FormControl(),
      menuWed: new FormControl(),
      menuThu: new FormControl(),
      menuFri: new FormControl(),

      //Text area- input price
      priceMon: new FormControl(),
      priceTue: new FormControl(),
      priceWed: new FormControl(),
      priceThu: new FormControl(),
      priceFri: new FormControl(),

      //Checkbox: veg & non-veg
      optionMonVeg: new FormControl(),
      optionMonNonVeg: new FormControl(),

      optionTueVeg: new FormControl(),
      optionTueNonVeg: new FormControl(),

      optionWedVeg: new FormControl(),
      optionWedNonVeg: new FormControl(),

      optionThuVeg: new FormControl(),
      optionThuNonVeg: new FormControl(),

      optionFriVeg: new FormControl(),
      optionFriNonVeg: new FormControl()
    });

    //Listing the menus for a specific week number
    let currentDate = new Date();
    let oneJan = new Date(currentDate.getFullYear(), 0, 1);
    // @ts-ignore
    let numberOfDays = Math.floor((currentDate - oneJan) / (24 * 60 * 60 * 1000));
    let result = Math.ceil((currentDate.getDay() + 1 + numberOfDays) / 7);

    let weekNum = new GetMenuList(result);
    this.formData.get("weekNo")?.setValue(result);
    this.getWeekMenu(weekNum);
  }

  //Reset btn
  onClickReset(data: any) {
    this.formData.reset();
  }

  //Submit btn
  onClickSubmit(data: any) {
    console.log(data);
    //array to store the following
    let menuDesc: string[] = [data.menuMon, data.menuTue, data.menuWed, data.menuThu, data.menuFri]
    let price: string[] = [data.priceMon, data.priceTue, data.priceWed, data.priceThu, data.priceFri]
    let day: string[] = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"]
    let optionVeg: string[] = [data.optionMonVeg ? "Veg;" : "", data.optionTueVeg ? "Veg;" : "", data.optionWedVeg ? "Veg;" : "", data.optionThuVeg ? "Veg;" : "", data.optionFriVeg ? "Veg;" : ""]
    let optionNonVeg: string[] = [data.optionMonNonVeg ? "NonVeg" : "", data.optionTueNonVeg ? "NonVeg" : "", data.optionWedNonVeg ? "NonVeg" : "", data.optionThuNonVeg ? "NonVeg" : "", data.optionFriNonVeg ? "NonVeg" : ""]
    let option: string
    //array to store all menus
    let menus = new Array<MenuCreation>;

    //looping through all orders
    for (let i in menuDesc) {
      //Concat to obtain option
      if (!(optionVeg[i] == "" && optionNonVeg[i] == "")) option = optionVeg[i] + '' + optionNonVeg[i]
      // @ts-ignore
      let menu = new MenuCreation(menuDesc[i], price[i], day[i], option, data.weekNo);
      menus.push(menu);
    }

    // faire appel à l'api
    //saving menus
    console.log(menus)
    this.menuService.postMenu(menus).subscribe((data: any) => {

        this.visibleMenuSave = true;
        setTimeout(() => {
          this.visibleMenuSave = false;
        }, 1500);
      },
      (error) => {
        console.log("error caught");
        this.visibleMenuNotSave = true;
        setTimeout(() => {
          this.visibleMenuNotSave = false;
        }, 1500);

      });
  }


  // For triggering PopUp for UserTypeValidation
  toggleLiveDemo() {
    this.modalVisibility = !this.modalVisibility;
    this._router.navigateByUrl('dashboard').then(() => {
    })
  }

  handleLiveDemoChange(event: boolean) {
    this.modalVisibility = event;
  }

  changeInWeekNum($event: Event) {
    console.log("weekNum changes")
    const formData = this.formData.getRawValue();
    let weekNum = new GetMenuList(formData.weekNo);
    this.getWeekMenu(weekNum);
  }

  getWeekMenu(weekNum: GetMenuList) {
    //api call
    this.menuService.getMenus(weekNum).subscribe((data: Array<Menu>) => {
      let i;

      if (data.length != 0) {
        //Making sure that the menus return in order of days
        for (i in data) switch (data[i].day) {
          case "Monday":
            this.menu[0] = data[i];
            break;
          case "Tuesday":
            this.menu[1] = data[i];
            break;
          case "Wednesday":
            this.menu[2] = data[i];
            break;
          case "Thursday":
            this.menu[3] = data[i];
            break;
          case "Friday":
            this.menu[4] = data[i];
            break;
        }
        //inserting veg & non-veg values
        this.existingVeg.map((value:string)=>this.formData.get(value)?.setValue(null));
        this.existingNonVeg.map((value:string)=>this.formData.get(value)?.setValue(null));
        //just to creat variables, below were autogenerated
        const {description, price} = this.menu[0];
        const {description: description1, price: price1} = this.menu[1];
        const {description: description2, price: price2} = this.menu[2];
        const {description: description3, price: price3} = this.menu[3];
        const {description: description4, price: price4} = this.menu[4];
        this.menuDescriptions = [description, description1, description2, description3, description4,]
        this.priceDescriptions = [price, price1, price2, price3, price4,]

        //Updating the values inserted if the need be
        for (i in this.menuDescriptions) {
          let option = this.menu[i].option.split(";",2)
          this.formData.get(this.existingMenuDescription[i])?.setValue(this.menuDescriptions[i]);
          this.formData.get(this.existingPrice[i])?.setValue(this.priceDescriptions[i]);
          for(const j in option){
            if(option[j]=='Veg') this.formData.get(this.existingVeg[i])?.setValue(true);
            if(option[j]=='NonVeg') this.formData.get(this.existingNonVeg[i])?.setValue(true);
          }
        }
      }
      //If no input, set everything null
      else {
        this.existingPrice.map((value:string)=>this.formData.get(value)?.setValue(""))
        this.existingMenuDescription.map((value:string)=>this.formData.get(value)?.setValue(""))
        this.existingVeg.map((value:string)=>this.formData.get(value)?.setValue(null));
        this.existingNonVeg.map((value:string)=>this.formData.get(value)?.setValue(null));
      }
    });
  }
}
