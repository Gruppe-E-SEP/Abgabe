import { CookieService } from 'ngx-cookie-service';
import { Component, OnInit } from '@angular/core';
import { Users } from "../../models/users.model";
import { UsersService } from "../../services/users.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {

  user: Users;

  constructor(private router: Router,
              private cookieService: CookieService,
              private userService: UsersService) {
    this.user = new Users()
  }

  onSubmit(benutzer: any) {
    const user = {
      email: benutzer.email,
      passwort: benutzer.passwort,
    }
    this.userService.loginUser(user).subscribe(result => {
      alert("Successful Login!");
      let expireDate = new Date();
      expireDate.setTime(expireDate.getTime() + (36000));
      this.cookieService.set('email', benutzer.email, expireDate, '/', 'localhost', false);
      this.router.navigate(['/dashboard']);
    });
  }

}
