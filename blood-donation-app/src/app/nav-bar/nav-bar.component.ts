import { Component } from '@angular/core';
import { AuthService } from '../authentication/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  constructor(private authService: AuthService){}

  isLoggedIn(){
    let loggedIn = this.authService.isLoggedIn();
    if(loggedIn){
      return !this.authService.tokenExpired(this.authService.getToken());
    }
    return false;
  }


}
