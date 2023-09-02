import { Component } from '@angular/core';
import { CenterService } from '../services/center.service';
import { Router } from '@angular/router';
import { AuthService } from '../authentication/auth.service';
import { AppointmentService } from '../services/appointment.service';

@Component({
  selector: 'app-centers',
  templateUrl: './centers.component.html',
  styleUrls: ['./centers.component.css']
})
export class CentersComponent {

  centers: any[]
  searchDate = ""
  role = ""

  constructor(private centerService: CenterService,
    private router: Router,
    private authService: AuthService,
    private appointmentService: AppointmentService) { }

  ngOnInit() {
    this.role = this.authService.getRole();
    this.centerService.getAll().subscribe((response: any) => {
      this.centers = response;
    })
  }

  centerDetail(id: any) {
    this.router.navigate(['/center', id]);
  }

  searchFreeCenters() {
    this.centerService.getFreeCenters(this.searchDate).subscribe((response: any) => {
      this.centers = response;
      console.log(this.centers)
    })
  }

  schedule(id: any) {
    let data = {
      centerId: id,
      donorUsername: this.authService.getUser(),
      startDateTime: this.searchDate
    }
    this.appointmentService.schedule(data).subscribe((response: any) => {
      console.log(response)
    })
  }
}
