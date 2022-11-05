import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Order } from '../../views/dashboard/Order';
import { Observable } from 'rxjs';
import { DailyConfirmation } from '../../views/dashboard/DailyConfirmation';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  rootURL = 'http://localhost:8080/api/order/';

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
    const token =localStorage.getItem('token');
    const header = {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${token}`).set('content-type', 'application/json')
    };
    const body = JSON.stringify(orders);
    return this.http.post<any>(this.rootURL + 'addWeekOrder', body,header);
  }

  removeOrder(daily: Array<DailyConfirmation>) :Observable<any>{
    const token =localStorage.getItem('token');
    const header = {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${token}`).set('content-type', 'application/json')
    };
    const body = JSON.stringify(daily);
    return this.http.post<any>(this.rootURL + 'deleteOrders', body,header);
  }
}
