import { Component, Input } from '@angular/core';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { Subject } from 'rxjs';
import { ScheduledAppointmentDetailComponent } from '../scheduled-appointment-detail/scheduled-appointment-detail.component';
import { NgbCalendar, NgbDateStruct, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-appointments-calendar',
  templateUrl: './appointments-calendar.component.html',
  styleUrls: ['./appointments-calendar.component.css']
})
export class AppointmentsCalendarComponent {

  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;
  viewDate: Date = new Date();
  refresh = new Subject<void>();

  model: NgbDateStruct;
	date: { year: number; month: number };

  @Input() events: CalendarEvent[];

  constructor(private modalService: NgbModal, private calendar: NgbCalendar) { 
    this.model = calendar.getToday()
  }

  showAppointmentDetails(event: CalendarEvent): void {
    const modalRef = this.modalService.open(ScheduledAppointmentDetailComponent, { centered: true });
    modalRef.componentInstance.event = event;
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  changeDateView(event: any){
    const jsDate = new Date(event.year, event.month - 1, event.day);
    
    this.viewDate = jsDate
  }

  navigate(event: any){
    this.date = event.next
    this.viewDate = new Date(this.date.year, this.date.month - 1, 1)
  }

}
