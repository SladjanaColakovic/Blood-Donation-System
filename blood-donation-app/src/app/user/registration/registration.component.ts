import { Component} from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import * as alertifyjs from 'alertifyjs';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],


})
export class RegistrationComponent {

  name: string
  surname: string
  jmbg: string
  email: string
  address: string
  city: string
  country: string
  phone: string
  gender = "Female"
  password: string
  confirmPassword: string
  submitted: boolean


  registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.pattern('[a-zčćžšđA-ZČĆŽŠĐ ]*')]),
    surname: new FormControl('', [Validators.required, Validators.pattern('[a-zčćžšđA-ZČĆŽŠĐ ]*')]),
    email: new FormControl('', [Validators.required, Validators.email]),
    jmbg: new FormControl('', [Validators.required, Validators.minLength(13), Validators.maxLength(13), Validators.pattern('[0-9]*')]),
    gender: new FormControl('Female', Validators.required),
    address: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    country: new FormControl('', Validators.required),
    phone: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),
    password: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$')]),
    confirmPassword: new FormControl('', Validators.required),
  })

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.submitted = false;
  }

  radioButtonChanged(value: any) {
    this.gender = value;
  }

  register() {
    this.submitted = true;
    if (this.registrationForm.valid) {
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
        role: "DONOR"
      }
      this.userService.register(data).subscribe((response: any) => {
        alertifyjs.set('notifier', 'position', 'bottom-center');
        alertifyjs.success('Uspješna registracija', 4);
        this.router.navigate(['/login'])
      }, error => {
        alertifyjs.set('notifier', 'position', 'bottom-center');
        alertifyjs.error(error.error, 10);
      })

    }
  }

}
