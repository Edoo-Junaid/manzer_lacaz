import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginRequest} from "./LoginRequest";
import {Observable} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private url ='http://192.168.31.82:8080/api/';
  constructor(private http:HttpClient) { }

  login(loginRequest:LoginRequest):Observable<any>{
    const contType="application/json"
    const headers = { 'content-type': contType}
    const body=JSON.stringify(loginRequest);
    return this.http.post(this.url+'auth/login',body,{'headers':headers})
  }

  logOut():Observable<any>{
    const contType="application/json"
    const headers = { 'content-type': contType}
    return this.http.get(this.url +'auth/logout',{'headers':headers});
  }
}
