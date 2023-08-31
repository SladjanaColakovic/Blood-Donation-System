import { Component, ViewChild } from '@angular/core';
import { UserService } from '../services/user.service';
import { NgbAlert } from '@ng-bootstrap/ng-bootstrap';
import { ErrorAlertComponent } from '../error-alert/error-alert.component';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],


})
export class RegistrationComponent {

  name = ""
  surname = ""
  jmbg = ""
  email = ""
  address = ""
  city = ""
  country = ""
  phone = ""
  gender = "Female"
  password = ""
  confirmPassword = ""
  
  message = ""

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  constructor(private userService: UserService) {

  }

  radioButtonChanged(value: any) {
    this.gender = value;
  }

  register() {
    let data = {
      name: this.name,
      surname: this.surname,
      username: this.email,
      jmbg: this.jmbg,
      gender: this.gender,
      address: this.address,
      city: this.city,
      country: this.country,
      phoneNumber: this.phone,
      password: this.password,
      confirmPassword: this.confirmPassword,
      role: "USER"
    }
    this.userService.register(data).subscribe((response: any) => {
    }, error => {
      this.message = "Neuspješna registracija"
      this.alertClosed = false
      this.alert.timeoutSet();
    })
  }

  closeAlert(event: any) {
    this.alertClosed = event
  }




}
