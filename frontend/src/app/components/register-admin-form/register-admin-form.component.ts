import { Component } from '@angular/core';
import {Admin} from "../../models/admin.model";
import {AdminService} from "../../services/admin.service";

@Component({
  selector: 'app-register-admin-form',
  templateUrl: './register-admin-form.component.html',
  styleUrls: ['./register-admin-form.component.scss']
})
export class RegisterAdminFormComponent {

  admin: Admin;

  constructor(private adminService: AdminService) { this.admin = new Admin()}

  onSubmit(administrator: any) {
    const admin = {
      vorname: administrator.vorname,
      nachname: administrator.nachname,
      email: administrator.email,
      passwort: administrator.passwort
    }

    console.log(admin);
    this.adminService.registerAdmin(admin).subscribe(result => {
      this.adminRegistered()
    });

  }
  adminRegistered() {
    window.location.href=('http://localhost:4200/');
    alert("HALLO Admin");
  }

}
