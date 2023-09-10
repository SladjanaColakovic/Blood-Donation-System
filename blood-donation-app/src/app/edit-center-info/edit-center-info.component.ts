import { Component, ViewChild } from '@angular/core';
import { CenterService } from '../services/center.service';
import { AuthService } from '../authentication/auth.service';
import { ErrorAlertComponent } from '../error-alert/error-alert.component';

@Component({
  selector: 'app-edit-center-info',
  templateUrl: './edit-center-info.component.html',
  styleUrls: ['./edit-center-info.component.css']
})
export class EditCenterInfoComponent {

  center: any = {} as any;

  message = ""

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  constructor(private centerService: CenterService, private authService: AuthService){}

  ngOnInit(){
    let username = this.authService.getUser();
    this.centerService.getManagerCenter(username).subscribe((response: any) => {
      this.center = response;
    })
  }

  editCenterInfo(){
    let data = {
      id: this.center.id,
      name: this.center.name,
      email: this.center.email,
      address: this.center.address,
      city: this.center.city,
      country: this.center.country,
      phoneNumber: this.center.phoneNumber,
      workingTimeFrom: this.center.workingTimeFrom,
      workingTimeTo: this.center.workingTimeTo,
      capacity: this.center.capacity,
      description: this.center.description

    }
    this.centerService.editCenterInfo(data).subscribe((response: any) => {
      this.center = response;
    }, error => {
      this.message = "Neuspješno ažuriranje podataka"
      this.alertClosed = false
      this.alert.timeoutSet();
    })
  }

  closeAlert(event: any) {
    this.alertClosed = event
  }

}
