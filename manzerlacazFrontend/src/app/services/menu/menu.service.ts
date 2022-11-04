import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {Menu} from "../../views/dashboard/Menu";
import {MenuCreation} from "../../views/charts/MenuCreation";
import {WeekNum} from "../../views/charts/WeekNum";

@Injectable({
  providedIn: 'root'
})
export class MenuService {
  private rootURL ='http://localhost:8080/api/menu/';
  constructor(private http:HttpClient) { }

  //User page & Admin Count page
  //To display the menus
  getMenus(menus: WeekNum) :Observable<any>{
    const token =localStorage.getItem('token');
    var header = {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer ${token}`).set('content-type','application/json')
    }
    const body = JSON.stringify(menus);
    return this.http.post<any>(this.rootURL + 'getCurrentMenu', body,header);
  }


  //Admin page
  //weekly menus
  postMenu(menus:Array<MenuCreation>) :Observable<any>{
    const token =localStorage.getItem('token');
    var header = {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer ${token}`).set('content-type','application/json')
    }
    const body = JSON.stringify(menus);
    return this.http.post<any>(this.rootURL + 'addMenu', body,header);
  }
}
