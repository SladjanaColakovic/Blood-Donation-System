import { Component } from '@angular/core';
import { AuthService } from '../authentication/auth.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  constructor(private authService: AuthService){}

  isLoggedIn(){
    let loggedIn = this.authService.isLoggedIn();
    if(loggedIn){
      return !this.authService.tokenExpired(this.authService.getToken());
    }
    return false;
  }

}
