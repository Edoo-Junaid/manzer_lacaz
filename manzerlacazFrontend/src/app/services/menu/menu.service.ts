import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from "rxjs";
import {Menu} from "../../views/dashboard/Menu";
import {MenuCreation} from "../../views/charts/MenuCreation";
import {GetMenuList} from "../../views/dashboard/GetMenuList";
@Injectable({
  providedIn: 'root'
})
export class MenuService {
  private rootURL ='http://192.168.31.82:8080/api/menu/';
  constructor(private http:HttpClient) { }

  getMenus(weekNum:GetMenuList):Observable<any>{
    const token =localStorage.getItem('token');
    const header = {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${token}`).set('content-type', 'application/json')
    };
    const body = JSON.stringify(weekNum);
    return this.http.post<Array<Menu>>(this.rootURL + 'getWeekMenu',body,header);
  }


  //Admin page
  //weekly menus
  postMenu(menus:Array<MenuCreation>) :Observable<any>{
    const token =localStorage.getItem('token');
    const header = {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${token}`).set('content-type', 'application/json')
    };
    const body = JSON.stringify(menus);
    return this.http.post<any>(this.rootURL + 'addMenu', body,header);
  }
}
