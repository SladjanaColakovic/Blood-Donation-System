import { Component, ViewChild } from '@angular/core';
import { AuthService } from '../authentication/auth.service';
import { ErrorAlertComponent } from '../error-alert/error-alert.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email = ""
  password = ""

  message = ""

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  constructor(private authService: AuthService) { }

  login() {

    let data = {
      username: this.email,
      password: this.password
    }

    this.authService.login(data).subscribe((response: any) => {
      localStorage.setItem('token', response.accessToken);
      window.location.href = "http://localhost:4200/"
    },
      error => {
        this.message = "Neispravno korisničko ime i lozinka"
        this.alertClosed = false
        this.alert.timeoutSet();
      });

  }

  closeAlert(event: any) {
    this.alertClosed = event
  }

}
