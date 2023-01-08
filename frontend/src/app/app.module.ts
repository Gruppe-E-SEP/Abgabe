import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { RegisterUserFormComponent } from './components/register-user-form/register-user-form.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterAdminFormComponent } from './components/register-admin-form/register-admin-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AddLigaFormComponent } from './components/add-liga-form/add-liga-form.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import {UsersService} from "./services/users.service";
import {HttpClientModule} from "@angular/common/http";
import {AdminService} from "./services/admin.service";
import { LoginFormComponent } from './components/login-form/login-form/login-form.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RegisterUserFormComponent,
    HomeComponent,
    RegisterAdminFormComponent,
    AddLigaFormComponent,
    DashboardComponent,
    LoginFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [
    UsersService,
    AdminService],
  bootstrap: [AppComponent]
})
export class AppModule { }