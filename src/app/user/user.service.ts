import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  rootURL = 'http://localhost:8080';

  postOrder(order: any) {
    return this.http.post<any>(this.rootURL + '/addOrder', order);
  }
}
