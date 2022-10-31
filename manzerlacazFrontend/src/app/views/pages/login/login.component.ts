import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {LoginService} from "../../../services/Login/login.service";
import {Router} from "@angular/router";
import {LoginRequest} from "../../../services/Login/LoginRequest";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})


export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService, private _router: Router) {
  }

  token!: string;
  username!: string;
  password!: string;
  formData!: FormGroup;

  ngOnInit(): void {
    localStorage.clear();
    this.formData = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    })
  }

  onClickLogin(data: any) {
    console.log(data);
    let loginRequest = new LoginRequest(data.username, data.password)
    this.loginService.login(loginRequest).subscribe((response) => {
        console.log(response);
        console.log("inside response");
        if (response.token) {
          localStorage.setItem('token', response.token);
          console.log("Token has been set");
          if (data.role == 'admin') {
            this._router.navigateByUrl('charts');
          } else {
            this._router.navigateByUrl('dashboard');
          }
        }
      },

      (error)=>{
        console.log("error caugth");
        console.log(error);
        this._router.navigateByUrl('404');
      })
  }
}


