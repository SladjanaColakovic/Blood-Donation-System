import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { ErrorAlertComponent } from 'src/app/error-alert/error-alert.component';
import { CenterService } from 'src/app/services/center.service';

@Component({
  selector: 'app-center-info-edit',
  templateUrl: './center-info-edit.component.html',
  styleUrls: ['./center-info-edit.component.css']
})
export class CenterInfoEditComponent {

  @Input() center: any = {} as any
  @Output() emitCenterChanged = new EventEmitter<any>();

  message = ""

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  constructor(private centerService: CenterService) { }

  editCenterInfo() {
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
      this.emitCenterChanged.emit(this.center);
      this.message = "Uspješno ažuriranje podataka"
      this.alertClosed = false
      this.alert.setAlertTime();
    }, error => {
      this.message = "Neuspješno ažuriranje podataka"
      this.alertClosed = false
      this.alert.setAlertTime();
    })
  }

  closeAlert(event: any) {
    this.alertClosed = event
  }
}
