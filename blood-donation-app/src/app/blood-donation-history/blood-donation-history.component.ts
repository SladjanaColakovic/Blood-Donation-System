import { Component } from '@angular/core';
import { AppointmentService } from '../services/appointment.service';
import { AuthService } from '../authentication/auth.service';

@Component({
  selector: 'app-blood-donation-history',
  templateUrl: './blood-donation-history.component.html',
  styleUrls: ['./blood-donation-history.component.css']
})
export class BloodDonationHistoryComponent {

  appointments: any[]

  constructor(private appointmentService: AppointmentService, private authService: AuthService){}

  ngOnInit(){
    this.appointmentService.getDonorAppointments(this.authService.getUser()).subscribe((response: any) => {
      this.appointments = response;
    })
  }
}
