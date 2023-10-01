import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppointmentService } from '../../services/appointment.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
import * as alertifyjs from 'alertifyjs';

@Component({
  selector: 'app-scheduled-appointment-detail',
  templateUrl: './scheduled-appointment-detail.component.html',
  styleUrls: ['./scheduled-appointment-detail.component.css']
})
export class ScheduledAppointmentDetailComponent {

  @Input() public event: any;

  isDonor = false;

  constructor(public activeModal: NgbActiveModal, private authService: AuthService, private appointmentService: AppointmentService, private router: Router) { }

  ngOnInit() {
    if (this.authService.getRole() === 'DONOR') {
      this.isDonor = true;
    }
  }

  cancel() {
    this.appointmentService.cancel(this.event.id).subscribe((response: any) => {
      this.activeModal.close();
      alertifyjs.set('notifier', 'position', 'bottom-center');
      alertifyjs.success('Uspješno otkazivanje termina', 1.2, () => {location.reload()});

    }, error => {
      alertifyjs.set('notifier', 'position', 'bottom-center');
      alertifyjs.error(error.error, 10);
    })
  }

  closeDialog() {
    this.activeModal.close()
  }

  isNot24HoursLater() {
    if (this.event.start > new Date(new Date().getTime() + 1 * 86400 * 1000)) {
      return true;
    }
    return false;
  }

}
