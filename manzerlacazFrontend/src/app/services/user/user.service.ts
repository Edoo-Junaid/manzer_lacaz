import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Order } from '../../views/dashboard/Order';
import { Observable } from 'rxjs';
import { Menu } from '../../views/dashboard/Menu';
import { DailyConfirmation } from '../../views/dashboard/DailyConfirmation';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  rootURL = 'http://localhost:8080/api/auth';

  // //Login- Avesh--------------------
  // getPublicContent(): Observable<any> {
  //   return this.http.get(this.rootURL + 'login', { responseType: 'text' });
  // }
  //
  // getUserBoard(): Observable<any> {
  //   return this.http.get(this.rootURL + 'user', { responseType: 'text' });
  // }
  //
  // getModeratorBoard(): Observable<any> {
  //   return this.http.get(this.rootURL + 'mod', { responseType: 'text' });
  // }
  //
  // getAdminBoard(): Observable<any> {
  //   return this.http.get(this.rootURL + 'admin', { responseType: 'text' });
  // }

  //Use cases- Aliya-----------------
  //weekly orders
  postOrder(orders:Array<Order>) :Observable<any>{
    const headers ={'content-type':'application/json'};
    const body = JSON.stringify(orders);
    return this.http.post<any>(this.rootURL + '/order/addWeekOrder', body,{'headers': headers});
  }

  //Displaying menus
  getMenus(){
    return this.http.get<Array<Menu>>(this.rootURL + '/menu/getCurrentMenu');
  }

  removeOrder(daily: Array<DailyConfirmation>) :Observable<any>{
    const headers ={'content-type':'application/json'};
    const body = JSON.stringify(daily);
    return this.http.post<any>(this.rootURL + '/order/deleteOrder', body,{'headers': headers});
  }
}
