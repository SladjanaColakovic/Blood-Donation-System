import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppointmentService } from '../services/appointment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-scheduled-appointment-detail',
  templateUrl: './scheduled-appointment-detail.component.html',
  styleUrls: ['./scheduled-appointment-detail.component.css']
})
export class ScheduledAppointmentDetailComponent {

  @Input() public event: any;

  constructor(public activeModal: NgbActiveModal, private appointmentService: AppointmentService, private router: Router){}

  ngOnInit(){
  }

  cancel(){
    this.appointmentService.cancel(this.event.id).subscribe((response: any) => {
      this.activeModal.close()
      location.replace("/scheduledAppointments")
    })
  }

}
