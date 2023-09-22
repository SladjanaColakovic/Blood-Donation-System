import { Component, ViewChild } from '@angular/core';
import { AuthService } from '../../authentication/auth.service';
import { ErrorAlertComponent } from '../../error-alert/error-alert.component';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email = ""
  password = ""
  message = ""
  submitted: boolean

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
  })

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.submitted = false;
  }

  login() {
    this.submitted = true;
    if (this.loginForm.valid) {

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
          this.alert.setAlertTimeError();
        });
    }
  }

  closeAlert(event: any) {
    this.alertClosed = event
  }

}
