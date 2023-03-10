import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Admin} from "../models/admin.model";
import {Users} from "../models/users.model";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  adminUrl:string;

  constructor(private http:HttpClient) { this.adminUrl='http://localhost:8080/administrator'}

  public registerAdmin(admin: Admin) {
    return this.http.post('http://localhost:8080/administrator/createSA', admin, {responseType: 'text'})
  }
  public loginAdmin(user : Users){
    return this.http.post("http://localhost:8080/administrator/login", user,{responseType: "text"})
  }
}
