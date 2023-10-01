import { Component} from '@angular/core';
import { AuthService } from '../../authentication/auth.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as alertifyjs from 'alertifyjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email = ""
  password = ""
  submitted: boolean

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
          alertifyjs.set('notifier', 'position', 'bottom-center');
          alertifyjs.error('Neispravno korisničko ime i lozinka', 15);
          
        });
    }
  }

}
