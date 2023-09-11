import { Component, Input } from '@angular/core';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { Subject } from 'rxjs';
import { ScheduledAppointmentDetailComponent } from '../scheduled-appointment-detail/scheduled-appointment-detail.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

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

  @Input() events: CalendarEvent[];

  constructor(private modalService: NgbModal) { }

  showAppointmentDetails(event: CalendarEvent): void {
    const modalRef = this.modalService.open(ScheduledAppointmentDetailComponent, { centered: true });
    modalRef.componentInstance.event = event;
  }

  setView(view: CalendarView) {
    this.view = view;
  }

}
