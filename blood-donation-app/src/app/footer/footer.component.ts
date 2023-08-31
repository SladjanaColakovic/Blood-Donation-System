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
    return this.authService.isLoggedIn();
  }

}
