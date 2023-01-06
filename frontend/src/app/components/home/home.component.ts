import { Component } from '@angular/core';
import {Users} from "../../models/users.model";
import {UsersService} from "../../services/users.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  constructor(private route: ActivatedRoute) {
  }



}
