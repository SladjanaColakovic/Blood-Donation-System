import { Component } from '@angular/core';
import { AppointmentService } from '../../services/appointment.service';
import { AuthService } from '../../authentication/auth.service';

@Component({
  selector: 'app-blood-donation-history-donor',
  templateUrl: './blood-donation-history-donor.component.html',
  styleUrls: ['./blood-donation-history-donor.component.css']
})
export class BloodDonationHistoryDonorComponent {

  appointments: any[]
  emptyResult = true;

  constructor(private appointmentService: AppointmentService, private authService: AuthService) { }

  ngOnInit() {
    this.appointmentService.getDonorAppointments(this.authService.getUser()).subscribe((response: any) => {
      this.appointments = response;
      if (this.appointments.length == 0) {
        this.emptyResult = true;
      }
      this.emptyResult = false;
    })
  }
}
