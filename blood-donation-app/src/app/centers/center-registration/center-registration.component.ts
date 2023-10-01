import { Component} from '@angular/core';
import { CenterService } from '../../services/center.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as alertifyjs from 'alertifyjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-center-registration',
  templateUrl: './center-registration.component.html',
  styleUrls: ['./center-registration.component.css']
})
export class CenterRegistrationComponent {

  centerName: string
  centerEmail: string
  centerAddress: string
  centerCity: string
  centerCountry: string
  centerPhone: string
  centerDescription: string
  centerCapacity: string
  workingTimeFrom: string
  workingTimeTo: string

  managerName: string
  managerSurname: string
  managerEmail: string
  managerAddress: string
  managerCity: string
  managerCountry: string
  managerPhone: string
  managerGender = "Female"
  managerPassword: string
  managerConfirmPassword: string

  submitted: boolean;

  centerRegistrationForm = new FormGroup({
    centerName: new FormControl('', Validators.required),
    centerEmail: new FormControl('', [Validators.required, Validators.email]),
    centerAddress: new FormControl('', Validators.required),
    centerCity: new FormControl('', Validators.required),
    centerCountry: new FormControl('', Validators.required),
    centerPhone: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),
    centerDescription: new FormControl('', Validators.required),
    centerCapacity: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),
    workingTimeFrom: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),
    workingTimeTo: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),
    managerName: new FormControl('', [Validators.required, Validators.pattern('[a-zčćžšđA-ZČĆŽŠĐ ]*')]),
    managerSurname: new FormControl('', [Validators.required, Validators.pattern('[a-zčćžšđA-ZČĆŽŠĐ ]*')]),
    managerEmail: new FormControl('', [Validators.required, Validators.email]),
    managerGender: new FormControl('Female', Validators.required),
    managerAddress: new FormControl('', Validators.required),
    managerCity: new FormControl('', Validators.required),
    managerCountry: new FormControl('', Validators.required),
    managerPhone: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),
    managerPassword: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$')]),
    managerConfirmPassword: new FormControl('', Validators.required),
  })

  constructor(private centerService: CenterService, private router: Router) { }

  ngOnInit() {
    this.submitted = false;
  }

  register() {
    this.submitted = true;
    if (this.centerRegistrationForm.valid) {
      let data = {
        name: this.centerName,
        email: this.centerEmail,
        address: this.centerAddress,
        city: this.centerCity,
        country: this.centerCountry,
        phoneNumber: this.centerPhone,
        workingTimeFrom: this.workingTimeFrom,
        workingTimeTo: this.workingTimeTo,
        capacity: this.centerCapacity,
        description: this.centerDescription,
        manager: {
          name: this.managerName,
          surname: this.managerSurname,
          username: this.managerEmail,
          password: this.managerPassword,
          confirmPassword: this.managerConfirmPassword,
          address: this.managerAddress,
          country: this.managerCountry,
          city: this.managerCity,
          role: "MANAGER",
          gender: this.managerGender,
          phoneNumber: this.managerPhone
        }
      }

      this.centerService.register(data).subscribe((response: any) => {
        alertifyjs.set('notifier', 'position', 'bottom-center');
        alertifyjs.success('Uspješna registracija centra', 4);
        this.router.navigate(['/centers'])
      }, error => {
        alertifyjs.set('notifier', 'position', 'bottom-center');
        alertifyjs.error(error.error, 15);
      })

    }

  }

  radioButtonChanged(value: any) {
    this.managerGender = value;
  }

}
