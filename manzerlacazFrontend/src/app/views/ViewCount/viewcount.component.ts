import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

import {MenuCreation} from "../charts/MenuCreation";
import {Router} from "@angular/router";
// @ts-ignore
import {MenuService} from "../../services/menu/menu.service";

@Component({
  selector: 'app-charts',
  templateUrl: './viewcount.html',
  styleUrls: ['./viewcount.component.scss']
})
export class ViewcountComponent implements OnInit{
 public modalVisibility =false;
  formData!: FormGroup;

  constructor(public menuService: MenuService,private _router: Router) {
  }

  ngOnInit(): void {

    if(localStorage.getItem('role')!='Admin'){
      console.log(localStorage.getItem('role'))
      console.log('modal should appear')
      this.modalVisibility=true;
    }
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
  }

  //Reset btn
  onClickReset(data: any){
    this.formData.reset();
  }

  //Submit btn

  onClickSubmit(data: any) {
    console.log(data);
    //array to store the following
    var menuDesc:string[] = new Array( data.menuMon,data.menuTue,data.menuWed,data.menuThu,data.menuFri)
    var price:string[] = new Array( data.priceMon,data.priceTue,data.priceWed,data.priceThu,data.priceFri)
    var day:string[] = new Array("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    var optionVeg:string[] = new Array(data.optionMonVeg? "Veg;":"",data.optionTueVeg? "Veg;":"",data.optionWedVeg? "Veg;":"",data.optionThuVeg? "Veg;":"",data.optionFriVeg? "Veg;":"")
    var optionNonVeg:string[] = new Array(data.optionMonNonVeg? "NonVeg":"",data.optionTueNonVeg? "NonVeg":"",data.optionWedNonVeg? "NonVeg":"",data.optionThuNonVeg? "NonVeg":"",data.optionFriNonVeg? "NonVeg":"")
    var option:string
    //array to store all menus
    var menus = new Array<MenuCreation>;

    //looping through all orders
    for (var i in menuDesc) {
      if (!(optionVeg[i] == "" && optionNonVeg[i] == "")){
        //Concat to obtain option
        option = optionVeg[i] + '' + optionNonVeg[i]
      }
      // @ts-ignore
      let  menu= new MenuCreation(menuDesc[i], price[i], day[i], option, weekNum);
      menus.push(menu);
    }

    // faire appel à l'api
    //saving menus
    console.log(menus)
    this.menuService.postMenu(menus).subscribe((data: any) => {
      console.log('message::::', data);
    });

  }


  // For triggering PopUp for UserTypeValidation
  toggleLiveDemo() {
this.modalVisibility=!this.modalVisibility;
  this._router.navigateByUrl('dashboard')
  }

  handleLiveDemoChange(event: boolean) {
      this.modalVisibility=event;
  }
}
