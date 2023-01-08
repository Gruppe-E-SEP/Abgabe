import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterUserFormComponent} from "./components/register-user-form/register-user-form.component";
import {RegisterAdminFormComponent} from "./components/register-admin-form/register-admin-form.component";
import {HomeComponent} from "./components/home/home.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {AddLigaFormComponent} from "./components/add-liga-form/add-liga-form.component";
import {LoginFormComponent} from "./components/login-form/login-form.component";
import {AdminLoginFormComponent} from "./components/admin-login-form/admin-login-form.component";

const routes: Routes = [

  {
    path:'',
    component: HomeComponent
  },
  {
    path:'user/register',
    component: RegisterUserFormComponent
  },
  {
    path:'admin/register',
    component: RegisterAdminFormComponent
  },
  {
    path:'dashboard',
    component: DashboardComponent,
    children: []
  },
  {
    path: 'liga',
    component: AddLigaFormComponent
  },
  {path : "user/login",
  component: LoginFormComponent
  },
  {
  path : "admin/login",
  component: AdminLoginFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
