import { Component } from '@angular/core';
import {Admin} from "../../models/admin.model";
import {AdminService} from "../../services/admin.service";
import { CookieService } from 'ngx-cookie-service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-admin-login-form',
  templateUrl: './admin-login-form.component.html',
  styleUrls: ['./admin-login-form.component.scss']
})
export class AdminLoginFormComponent {

  admin: Admin;

  constructor(private router: Router,
              private cookieService: CookieService,
              private userService: AdminService) { this.admin = new Admin()}

  onSubmit(administrator: any) {
    const admin = {

      email: administrator.email,
      passwort: administrator.passwort
    }
    this.userService.loginAdmin(admin).subscribe(result => {
      alert("Successful Login!");
      let expireDate = new Date();
      expireDate.setTime(expireDate.getTime() + (36000));
      this.cookieService.set('email', administrator.email, expireDate, '/', 'localhost', false);
      this.router.navigate(['/dashboard']);
    });
  }
  }



