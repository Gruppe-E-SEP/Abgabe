import { Component } from '@angular/core';
import { CookieService} from "ngx-cookie-service";
import { CookieOptions } from "ngx-cookie-service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent {
  title = 'frontend';

  constructor(private cookieService: CookieService){
    this.setCookie();
    this.deleteCookie();
    this.deleteAll();
  }
  setCookie(){
    this.cookieService.set("technical_cookie", "This is a cookie!");
  }

  getCookie(){
    this.cookieService.get("Technical_cookie");
  }
  getAllCookies(){
    this.cookieService.getAll();
  }

  deleteCookie(){
    this.cookieService.delete("technical_cookie");
  }

  deleteAll(){
    this.cookieService.deleteAll();
  }

  checkCookie(){
    var cookieExists =
      this.cookieService.check("technical_cookie");
    console.log("Cookie Exists", cookieExists);
  }
}

