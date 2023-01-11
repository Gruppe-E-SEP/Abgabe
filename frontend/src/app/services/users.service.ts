import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Users} from "../models/users.model";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  usersUrl:string;


  constructor(private  http:HttpClient) { this.usersUrl='http://localhost:8080/user' }

  public getAll(): Observable<Users[]> {
    return this.http.get<Users[]>('http://localhost:8080/user/all');
  }

  public registerUser(user: Users) {
    return this.http.post('http://localhost:8080/user/create', user,{responseType: 'text'});
  }
  public loginUser(user : Users){
    return this.http.post("http://localhost:8080/user/login", user,{responseType: "text"})
  }


}
