import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
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
  
 
  timeoutSet(){
    setTimeout(() => this.staticAlert.close(), 15000);
  }

  closeAlert(){
    this.alertClosed = true;
    this.emitAlertClosed.emit(true)
  }



}
