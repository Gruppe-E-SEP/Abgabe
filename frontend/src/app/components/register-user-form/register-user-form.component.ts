import {Component, OnInit} from '@angular/core';
import {Users} from "../../models/users.model";
import {UsersService} from "../../services/users.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-register-user-form',
  templateUrl: './register-user-form.component.html',
  styleUrls: ['./register-user-form.component.scss']
})
export class RegisterUserFormComponent {

  user:Users;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UsersService) { this.user = new Users()}


  onSubmit(benutzer:any) {
    const user = {
      vorname: benutzer.vorname,
      nachname: benutzer.nachname,
      email: benutzer.email,
      passwort: benutzer.passwort,
      date: benutzer.date
    }

    console.log(user);
    this.userService.registerUser(user).subscribe(result=>{this.userRegistered()});

  }

  userRegistered() {
    window.location.href=('http://localhost:4200/');
    alert("HALLO User");
  }


}




