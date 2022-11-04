import {Component, Input, OnInit} from '@angular/core';

import { ClassToggleService, HeaderComponent } from '@coreui/angular';
import {LoginService} from "../../../services/Login/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-default-header',
  templateUrl: './default-header.component.html',
})
export class DefaultHeaderComponent extends HeaderComponent implements OnInit{

  @Input() sidebarId: string = "sidebar";
  public userType!:any;


  constructor(private classToggler: ClassToggleService,private loginService: LoginService, private _router: Router) {
    super();
  }

  ngOnInit(): void {
      this.userType=localStorage.getItem('role');
  }

  Logout($event: MouseEvent) {
    this.loginService.logOut();
    localStorage.clear();
    this._router.navigateByUrl('login')
    console.log($event)
  }
}
