import { Component} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/authentication/auth.service';
import { UserService } from 'src/app/services/user.service';
import * as alertifyjs from 'alertifyjs';

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
    if(this.editPasswordForm.valid && this.password === this.confirmPassword){
      let data = {
        password: this.password,
        confirmPassword: this.confirmPassword,
        username: this.username
      }
      this.userService.changePassword(data).subscribe((response: any) => {
        alertifyjs.set('notifier', 'position', 'bottom-center');
        alertifyjs.success('Uspješno ste ažurirali lozinku', 4);
        this.authService.logout();
      }, error => {
        alertifyjs.set('notifier', 'position', 'bottom-center');
        alertifyjs.error(error.error, 15);
      })
    }

  }

}
