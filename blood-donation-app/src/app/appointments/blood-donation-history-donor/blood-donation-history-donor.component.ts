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

  searchDate = "";
  text = ""
  sortBy = "center"
  sortDirection = "ascending"

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

  sort(sortBy: any, sortDirection: any){
    this.sortBy = sortBy;
    this.sortDirection = sortDirection;
    let data = {
      sortBy: sortBy,
      sortDirection: sortDirection,
      donorUsername: this.authService.getUser(),
      searchText: this.text,
      searchDate: this.searchDate
    }
    this.appointmentService.sortDonorAppointments(data).subscribe((response: any) => {
      this.appointments = response;
    })
  }

  searchByCenterOrAddress(){
    let data = {
      sortBy: this.sortBy,
      sortDirection: this.sortDirection,
      donorUsername: this.authService.getUser(),
      searchText: this.text,
      searchDate: this.searchDate

    }
    this.appointmentService.sortDonorAppointments(data).subscribe((response: any) => {
      this.appointments = response;
    })
  }
}
