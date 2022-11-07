import { Component, OnInit } from '@angular/core';
import { FormGroup} from '@angular/forms';
import {Router} from "@angular/router";
import {Menu} from "../dashboard/Menu";
import {MenuService} from "../../services/menu/menu.service";
import {GetMenuList} from "../dashboard/GetMenuList";

@Component({
  selector: 'app-charts',
  templateUrl: './viewcount.html',
  styleUrls: ['./viewcount.component.scss']
})
export class ViewcountComponent implements OnInit {
  menu!: Menu[];
  menuDescriptions!: string[];
  priceDescriptions!: string[];
  //menu id
  menuMonDesc!: string;
  menuTueDesc!: string;
  menuWedDesc!: string;
  menuThuDesc!: string;
  menuFriDesc!: string;

  menuMon!: Menu;
  menuTue!: Menu;
  menuWed!: Menu;
  menuThu!: Menu;
  menuFri!: Menu;
  formData!: FormGroup;

  constructor(public menuService: MenuService, private _router: Router) {
  }

  ngOnInit(): void {
    //Displaying menus according to id
    let weekNum = new GetMenuList(47);
    this.menuService.getMenus(weekNum).subscribe((data: Array<Menu>) => {
      this.menu = [data[0], data[1], data[2], data[3], data[4]];
      this.menuDescriptions = [data[0].description, data[1].description, data[2].description, data[3].description, data[4].description,]
      this.priceDescriptions = [data[0].price, data[1].price, data[2].price, data[3].price, data[4].price,]
      console.log(this.menu)
    });
  }
}








