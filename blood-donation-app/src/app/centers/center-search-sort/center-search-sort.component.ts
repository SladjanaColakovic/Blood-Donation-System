import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
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

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  constructor(private centerService: CenterService){}

  searchFreeCenters() {
    this.centerService.getFreeCenters(this.searchDate).subscribe((response: any) => {
      this.centers = response;
      console.log(this.centers)
      this.emitCenters.emit(this.centers)
      if(this.centers.length == 0){
        this.emitEmptyResult.emit(true)
      }
      this.emitEmptyResult.emit(false)
      this.emitDateTime.emit(this.searchDate)
    }, error => {
      this.message = "Neuspješno pretraživanje slobodnih centara za odabrani datum"
      this.alertClosed = false
      this.alert.setAlertTime();
    })
  }

  closeAlert(event: any) {
    this.alertClosed = event
  }
}
