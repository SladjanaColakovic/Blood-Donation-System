import { Component, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/authentication/auth.service';
import { ErrorAlertComponent } from 'src/app/error-alert/error-alert.component';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-password-edit',
  templateUrl: './user-password-edit.component.html',
  styleUrls: ['./user-password-edit.component.css']
})
export class UserPasswordEditComponent {

  submitted: boolean;
  password: string;
  confirmPassword: string;
  username: string

   message = "";

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  editPasswordForm = new FormGroup({
    password: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$')]),
    confirmPassword: new FormControl('', Validators.required),
  })

  constructor(private authService: AuthService, private userService: UserService) { }

  ngOnInit() {
    this.submitted = false;
    this.username = this.authService.getUser();

  }

  editPassword() {
    this.submitted = true;
    if(this.editPasswordForm.valid){
      let data = {
        password: this.password,
        confirmPassword: this.confirmPassword,
        username: this.username
      }
      this.userService.changePassword(data).subscribe((response: any) => {
        this.authService.logout();
      }, error => {
        this.message = error.error;
        this.alertClosed = false;
        this.alert.setAlertTimeError();
      })
    }

  }

  closeAlert(event: any) {
    this.alertClosed = event
  }

}
