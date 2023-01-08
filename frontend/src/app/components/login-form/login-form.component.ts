

import {Component, OnInit} from '@angular/core';
import {Users} from "../../models/users.model";
import {UsersService} from "../../services/users.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {

  user:Users;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UsersService) { this.user = new Users()}


  onSubmit(benutzer:any) {
    const user = {
      email: benutzer.email,
      passwort: benutzer.passwort,

    }

    console.log(user);
    this.userService.loginUser(user).subscribe(result=>{this.userLoggedIn()});

  }

  userLoggedIn() {
    window.location.href=('http://localhost:4200/');
    alert("Successful Login!");
  }


}
