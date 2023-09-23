import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { AuthService } from 'src/app/authentication/auth.service';
import { ErrorAlertComponent } from 'src/app/error-alert/error-alert.component';
import { CenterService } from 'src/app/services/center.service';

@Component({
  selector: 'app-center-search-sort',
  templateUrl: './center-search-sort.component.html',
  styleUrls: ['./center-search-sort.component.css']
})
export class CenterSearchSortComponent {

  @Input() searchDate = ""
  @Output() emitCenters = new EventEmitter<any>();
  @Output() emitEmptyResult = new EventEmitter<boolean>();
  @Output() emitDateTime = new EventEmitter<any>();
  centers: any[]

  message = ""

  center = "";
  address = "";
  role = ""
  sortBy = "center"
  sortDirection = "ascending"

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  constructor(private centerService: CenterService, private authService: AuthService) {
    this.role = authService.getRole();
  }

  searchFreeCenters() {
    this.centerService.getFreeCenters(this.searchDate, this.center, this.address, this.sortBy, this.sortDirection).subscribe((response: any) => {
      this.centers = response;
      this.emitCenters.emit(this.centers)
      if (this.centers.length == 0) {
        this.emitEmptyResult.emit(true)
      } else {
        this.emitEmptyResult.emit(false)
      }
      this.emitDateTime.emit(this.searchDate)
    }, error => {
      this.message = "Neuspješno pretraživanje slobodnih centara za odabrani datum"
      this.alertClosed = false
      this.alert.setAlertTimeError();
    })
  }

  sort(sortBy: any, sortDirection: any) {
    this.sortBy = sortBy;
    this.sortDirection = sortDirection;
    this.centerService.getFreeCenters(this.searchDate, this.center, this.address, this.sortBy, this.sortDirection).subscribe((response: any) => {
      this.centers = response;
      this.emitCenters.emit(this.centers)
      if (this.centers.length == 0) {
        this.emitEmptyResult.emit(true)
      } else {
        this.emitEmptyResult.emit(false)
      }
      this.emitDateTime.emit(this.searchDate)
    }, error => {
      this.message = error.error
      this.alertClosed = false
      this.alert.setAlertTimeError();
    })

  }

  closeAlert(event: any) {
    this.alertClosed = event
  }
}
