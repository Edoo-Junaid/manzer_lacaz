import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from "rxjs";
import {Menu} from "../../views/dashboard/Menu";
import {MenuCreation} from "../../views/charts/MenuCreation";
@Injectable({
  providedIn: 'root'
})
export class MenuService {
  private rootURL ='http://localhost:8080/api/menu/';
  constructor(private http:HttpClient) { }

  getMenus(){
    const token =localStorage.getItem('token');
    var header = {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer ${token}`).set('content-type','application/json')
    }
    return this.http.get<Array<Menu>>(this.rootURL + 'getCurrentMenu',header);
  }


  //Admin page
  //weekly menus
  postMenu(menus:Array<MenuCreation>) :Observable<any>{
    const headers ={'content-type':'application/json'};
    const body = JSON.stringify(menus);
    return this.http.post<any>(this.rootURL + '/menu/addMenu', body,{'headers': headers});
  }
}