import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {MenuService} from "../../services/menu/menu.service";
import {Menu} from "../dashboard/Menu";
import {Option} from "../viewcount/Option";
import {OrderService} from "../../services/order/order.service";
import {MenuCreation} from "../charts/MenuCreation";
import {WeekNum} from "../charts/WeekNum";
import {GetMenuList} from "../dashboard/GetMenuList";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-charts',
  templateUrl: './viewcount.html',
  styleUrls: ['./viewcount.component.scss']
})
export class ViewcountComponent implements OnInit {
  menu!: Menu[];
  weekNum!: number;
  countVeg:any =[0,0,0,0,0];
  countNonVeg:any =[0,0,0,0,0];
  public dayArray: string[] = new Array("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

  menuDescriptions!:string[];
  priceDescriptions!:string[];
  menuMon!: Menu;
  menuTue!: Menu;
  menuWed!: Menu;
  menuThu!: Menu;
  menuFri!: Menu;
  formData!: FormGroup;

  constructor(public menuService: MenuService, public orderService: OrderService, private _router: Router) {
  }

  ngOnInit(): void {

    //Form variables
    this.formData = new FormGroup({
      //Number area-input week number
      weekNo: new FormControl()
    });

    this.formData.get('weekNo')?.setValue(47);
    this.changeInWeekNum(1)

    // let weekNum = new GetMenuList(this.weekNum);
    // //Displaying menus according to id
    // this.menuService.getMenus(weekNum).subscribe((data: Array<Menu>) => {
    //   this.menu = [data[0], data[1], data[2], data[3], data[4]];
    //   this.menuDescriptions = [data[0].description, data[1].description, data[2].description, data[3].description, data[4].description,]
    //   console.log(this.menu)
    // });

    //Displaying the count of options
    var day:string[] = new Array("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    for(var i in day){
      let optionVeg = new Option("non-veg",day[i],this.weekNum);

      console.log("************************")
      this.orderService.getCount(optionVeg).subscribe((data: any) => {
        console.log(data)
        this.countVeg[i]=data;
      });
      console.log(this.countNonVeg);

      let  optionNonVeg = new Option("veg",day[i],47);
      this.orderService.getCount(optionNonVeg).subscribe((data: any) => {
        console.log(data)
        this.countNonVeg[i]=data;
      });
      console.log(this.countVeg)
    }
  }

  changeInWeekNum($event: any) {
    console.log("weekNum changes")
    const formData = this.formData.getRawValue();
    console.log(formData);
    let weekNum = new GetMenuList(formData.weekNo);
    console.log(weekNum);
    let subscription = this.menuService.getMenus(weekNum).subscribe((data: Array<Menu>) => {
      console.log(data)
      if(data.length!=0) {
        for (const datum of data) {
          if (datum.day == "Monday") this.menu[0] = datum;
          if (datum.day == "Tuesday") this.menu[1] = datum;
          if (datum.day == "Wednesday") this.menu[2] = datum;
          if (datum.day == "Thursday") this.menu[3] = datum;
          if (datum.day == "Friday") this.menu[4] = datum;
        }
        this.menuDescriptions = [this.menu[0].description, this.menu[1].description, this.menu[2].description, this.menu[3].description, this.menu[4].description,]
        this.priceDescriptions = [this.menu[0].price, this.menu[1].price, this.menu[2].price, this.menu[3].price, this.menu[4].price,]
      }
      else{
        for(var  i in this.menuDescriptions){
          this.menuDescriptions[i]="";
          this.priceDescriptions[i]="";
        }
      }
    });
  }
}








