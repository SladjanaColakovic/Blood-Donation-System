import { Component, Input, ViewChild } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppointmentService } from '../../services/appointment.service';
import { Router } from '@angular/router';
import { ErrorAlertComponent } from '../../error-alert/error-alert.component';
import { AuthService } from 'src/app/authentication/auth.service';

@Component({
  selector: 'app-scheduled-appointment-detail',
  templateUrl: './scheduled-appointment-detail.component.html',
  styleUrls: ['./scheduled-appointment-detail.component.css']
})
export class ScheduledAppointmentDetailComponent {

  @Input() public event: any;

  message = ""
  isDonor = false;
  canceled = false;

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  constructor(public activeModal: NgbActiveModal, private authService: AuthService, private appointmentService: AppointmentService, private router: Router) { }

  ngOnInit() {
    if (this.authService.getRole() === 'USER') {
      this.isDonor = true;
    }
  }

  cancel() {
    this.appointmentService.cancel(this.event.id).subscribe((response: any) => {
      //this.activeModal.close()
      this.canceled = true;
      // this.message = "Uspješno otkazivanje termina"
      // this.alertClosed = false
      // this.alert.setAlertTimeError()
      //location.replace("/scheduledAppointments")
    }, error => {
      this.message = "Neuspješno otkazivanje termina"
      this.alertClosed = false
      this.alert.setAlertTimeError();
    })
  }

  closeAlert(event: any) {
    this.alertClosed = event
    location.replace("/scheduledAppointments")
  }

  closeDialog() {
    this.activeModal.close()
    if (this.canceled) {
      location.replace("/scheduledAppointments")
    }
  }


}
