import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AuthService } from 'src/app/authentication/auth.service';
import { CenterService } from 'src/app/services/center.service';
import * as alertifyjs from 'alertifyjs';

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

  center = "";
  address = "";
  role = ""
  sortBy = "center"
  sortDirection = "ascending"

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
      alertifyjs.set('notifier', 'position', 'bottom-center');
      alertifyjs.error('Neuspješno pretraživanje slobodnih centara za odabrani datum', 15);
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
      alertifyjs.set('notifier', 'position', 'bottom-center');
      alertifyjs.error(error.error, 15);
    })

  }

}
