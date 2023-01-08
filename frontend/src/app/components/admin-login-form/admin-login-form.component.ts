import { Component } from '@angular/core';
import {Admin} from "../../models/admin.model";
import {AdminService} from "../../services/admin.service";

@Component({
  selector: 'app-admin-login-form',
  templateUrl: './admin-login-form.component.html',
  styleUrls: ['./admin-login-form.component.scss']
})
export class AdminLoginFormComponent {

  admin: Admin;

  constructor(private adminService: AdminService) { this.admin = new Admin()}

  onSubmit(administrator: any) {
    const admin = {

      email: administrator.email,
      passwort: administrator.passwort
    }

    console.log(admin);
    this.adminService.registerAdmin(admin).subscribe(result => {
      this.adminLoggedIn()
    });

  }
  adminLoggedIn() {
    window.location.href=('http://localhost:4200/');
    alert(" Admin eingeloggt");
  }

}

