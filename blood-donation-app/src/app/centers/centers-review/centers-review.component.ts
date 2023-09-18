import { Component, ViewChild } from '@angular/core';
import { CenterService } from '../../services/center.service';
import { Router } from '@angular/router';
import { AuthService } from '../../authentication/auth.service';
import { AppointmentService } from '../../services/appointment.service';
import { ErrorAlertComponent } from '../../error-alert/error-alert.component';

@Component({
  selector: 'app-centers',
  templateUrl: './centers-review.component.html',
  styleUrls: ['./centers-review.component.css']
})
export class CentersReviewComponent {

  centers: any[]
  searchDate = ""
  role = ""
  emptyResult = true;

  message = ""

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  constructor(private centerService: CenterService,
    private router: Router,
    private authService: AuthService,
    private appointmentService: AppointmentService) { }

  ngOnInit() {
    this.role = this.authService.getRole();
    this.centerService.getAll().subscribe((response: any) => {
      this.centers = response;
      if (this.centers.length == 0) {
        this.emptyResult = true;
      }
      this.emptyResult = false;
    })
  }

  centerDetail(id: any) {
    this.router.navigate(['/center', id]);
  }

  schedule(id: any) {
    let data = {
      centerId: id,
      donorUsername: this.authService.getUser(),
      startDateTime: this.searchDate
    }
    this.appointmentService.schedule(data).subscribe((response: any) => {
      this.message = "Uspješno ste zakazali termin"
      this.alertClosed = false
      this.alert.setAlertTime('/centers');
    }, error => {
      this.message = "Neuspješno zakazivanje termina"
      this.alertClosed = false
      this.alert.setAlertTimeError();
    })
  }

  closeAlert(event: any) {
    this.alertClosed = event
  }

  searchedCenters(event: any){
    this.centers = event;
  }

  emptyResultChanged(event: any){
    this.emptyResult = event;
  }

  changeDateTime(newDateTime: any){
    this.searchDate = newDateTime
  }
}
