import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {MenuService} from "../../services/menu/menu.service";
import {Menu} from "../dashboard/Menu";
import {Option} from "../viewcount/Option";
import {OrderService} from "../../services/order/order.service";
import {MenuCreation} from "../charts/MenuCreation";
import {WeekNum} from "../charts/WeekNum";

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

  menuDescriptions!: string[];
  menuMon!: Menu;
  menuTue!: Menu;
  menuWed!: Menu;
  menuThu!: Menu;
  menuFri!: Menu;

  constructor(public menuService: MenuService, public orderService: OrderService, private _router: Router) {
  }

  ngOnInit(): void {

    let menu = new WeekNum(this.weekNum);
    //Displaying menus according to id
    this.menuService.getMenus(menu).subscribe((data: Array<Menu>) => {
      this.menu = [data[0], data[1], data[2], data[3], data[4]];
      this.menuDescriptions = [data[0].description, data[1].description, data[2].description, data[3].description, data[4].description,]
      console.log(this.menu)
    });

    //Displaying the count of options
    //array for days
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
}








