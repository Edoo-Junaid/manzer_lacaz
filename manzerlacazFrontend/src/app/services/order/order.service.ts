import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Order } from '../../views/dashboard/Order';
import { Option } from '../../views/viewCount/Option';
import { Observable } from 'rxjs';
import { DailyConfirmation } from '../../views/dashboard/DailyConfirmation';
import {GetOrderList} from "../../views/dashboard/GetOrderList";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  rootURL = 'http://192.168.31.82:8080/api/order/';

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

  removeOrder(daily: DailyConfirmation) :Observable<any>{
    const token =localStorage.getItem('token');
    const header = {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${token}`).set('content-type', 'application/json')
    };
    const body = JSON.stringify(daily);
    return this.http.post<any>(this.rootURL + 'deleteOrder', body,header);
  }
  getOrders(orderList:GetOrderList):Observable<any>{
    const token =localStorage.getItem('token');
    const header = {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${token}`).set('content-type', 'application/json')
    };
    const body = JSON.stringify(orderList);
    return this.http.post<Array<Order>>(this.rootURL + 'getExistingOrder',body,header);
  }

  //Retrieving count of options
  getCount(count: Option) :Observable<any>{
    const token =localStorage.getItem('token');
    var header = {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer ${token}`).set('content-type','application/json')
    }
    const body = JSON.stringify(count);
    console.log(body);
    return this.http.post<any>(this.rootURL + 'getOrderOptionCountByDay', body,header);
  }

}
