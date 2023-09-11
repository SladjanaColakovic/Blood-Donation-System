import { Component, Input, ViewChild } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppointmentService } from '../../services/appointment.service';
import { Router } from '@angular/router';
import { ErrorAlertComponent } from '../../error-alert/error-alert.component';

@Component({
  selector: 'app-scheduled-appointment-detail',
  templateUrl: './scheduled-appointment-detail.component.html',
  styleUrls: ['./scheduled-appointment-detail.component.css']
})
export class ScheduledAppointmentDetailComponent {

  @Input() public event: any;
  
  message = ""

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  constructor(public activeModal: NgbActiveModal, private appointmentService: AppointmentService, private router: Router) { }

  cancel() {
    this.appointmentService.cancel(this.event.id).subscribe((response: any) => {
      this.activeModal.close()
      location.replace("/scheduledAppointments")
    }, error => {
      this.message = "Neuspješno otkazivanje termina"
      this.alertClosed = false
      this.alert.setAlertTime();
    })
  }

  closeAlert(event: any) {
    this.alertClosed = event
  }

}
