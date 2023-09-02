import { Component } from '@angular/core';
import { CenterService } from '../services/center.service';

@Component({
  selector: 'app-center-registration',
  templateUrl: './center-registration.component.html',
  styleUrls: ['./center-registration.component.css']
})
export class CenterRegistrationComponent {

  url: any;
  public selectedFile

  centerName = ""
  centerEmail = ""
  centerAddress = ""
  centerCity = ""
  centerCountry = ""
  centerPhone = ""
  centerDescription = ""
  centerCapacity = ""
  workingTimeFrom = ""
  workingTimeTo = ""

  managerName = ""
  managerSurname = ""
  managerEmail = ""
  managerAddress = ""
  managerCity = ""
  managerCountry = ""
  managerPhone = ""
  managerGender = "Female"
  managerPassword = ""
  managerConfirmPassword = ""


  constructor(private centerService: CenterService) { }

  ngOnInit(){
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

    const formData = new FormData();
    formData.append("center", new Blob([JSON.stringify(data)],{type: "application/json"}))
    formData.append("image", this.selectedFile, this.selectedFile.name);

    this.centerService.register(formData).subscribe((response: any) => {
      console.log(response)
    })

  }

  radioButtonChanged(value: any) {
    this.managerGender = value;
  }


}
