import { Component, ViewChild } from '@angular/core';
import { CenterService } from '../../services/center.service';
import { ErrorAlertComponent } from '../../error-alert/error-alert.component';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-center-registration',
  templateUrl: './center-registration.component.html',
  styleUrls: ['./center-registration.component.css']
})
export class CenterRegistrationComponent {

  url: any;
  public selectedFile

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

  message = ""

  submitted: boolean;

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

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

  constructor(private centerService: CenterService) { }

  ngOnInit() {
    this.submitted = false;
  }

  selectFile(event: any) {
    this.selectedFile = event.target.files[0];
    if (!event.target.files[0] || event.target.files[0].length == 0) {
      return;
    }

    var mimeType = event.target.files[0].type;

    if (mimeType.match(/image\/*/) == null) {
      return;
    }

    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);

    reader.onload = (_event) => {

      this.url = reader.result;
    }
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

      /*const formData = new FormData();
      formData.append("center", new Blob([JSON.stringify(data)], { type: "application/json" }))
      formData.append("image", this.selectedFile, this.selectedFile.name);*/

      this.centerService.register(data).subscribe((response: any) => {
        console.log(response)
      }, error => {
        this.message = "Neuspješna registracija centra"
        this.alertClosed = false
        this.alert.setAlertTime();
      })

    }

  }

  radioButtonChanged(value: any) {
    this.managerGender = value;
  }

  closeAlert(event: any) {
    this.alertClosed = event
  }

}
