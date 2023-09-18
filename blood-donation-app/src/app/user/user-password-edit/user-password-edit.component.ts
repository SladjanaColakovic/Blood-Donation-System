import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-password-edit',
  templateUrl: './user-password-edit.component.html',
  styleUrls: ['./user-password-edit.component.css']
})
export class UserPasswordEditComponent {

  submitted: boolean;
  password: string;
  confirmPassword: string;

  editPasswordForm = new FormGroup({
    password: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$')]),
    confirmPassword: new FormControl('', Validators.required),
  })

  constructor() { }

  ngOnInit() {
    this.submitted = false;
  }

  editPassword() {
    this.submitted = true;
  }

}
