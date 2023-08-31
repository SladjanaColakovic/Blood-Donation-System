import { Component } from '@angular/core';
import { AuthService } from '../authentication/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email = ""
  password = ""

  constructor(private authService: AuthService){}

  login(){

    let data = {
      username: this.email,
      password: this.password
    }

    this.authService.login(data);

  }

}
