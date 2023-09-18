import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgbAlert } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-error-alert',
  templateUrl: './error-alert.component.html',
  styleUrls: ['./error-alert.component.css']
})
export class ErrorAlertComponent {
  
  @ViewChild('staticAlert', { static: false }) staticAlert: NgbAlert;

  @Input() message = ""
  @Input() alertClosed = true
  @Output() emitAlertClosed = new EventEmitter<boolean>();

  url = "";

  constructor(private router: Router){}

  
  setAlertTime(url: any){
    this.url = url;
    setTimeout(() => this.closeAlertAndNavigate(), 15000);
  }

  setAlertTimeError(){
    setTimeout(() => this.closeAlert(), 15000);
  }

  closeAlert(){
    this.alertClosed = true;
    this.emitAlertClosed.emit(true);
    if(this.url != ""){
      this.router.navigate([this.url]);
    }
  }

  closeAlertAndNavigate(){
    this.alertClosed = true;
    this.emitAlertClosed.emit(true);
    this.router.navigate([this.url]);

  }



}
