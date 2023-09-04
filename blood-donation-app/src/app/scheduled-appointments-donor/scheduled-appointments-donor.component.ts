import { Component } from '@angular/core';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { addMinutes, isSameDay, isSameMonth } from 'date-fns';
import { Subject, map } from 'rxjs';
import { EventColor } from 'calendar-utils';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ScheduledAppointmentDetailComponent } from '../scheduled-appointment-detail/scheduled-appointment-detail.component';
import { AppointmentService } from '../services/appointment.service';
import { AuthService } from '../authentication/auth.service';
import { start } from '@popperjs/core';

const colors: Record<string, EventColor> = {
  red: {
    primary: '#d17272',
    secondary: '#FAE3E3',
  },
};

@Component({
  selector: 'app-scheduled-appointments-donor',
  templateUrl: './scheduled-appointments-donor.component.html',
  styleUrls: ['./scheduled-appointments-donor.component.css']
})


export class ScheduledAppointmentsDonorComponent {

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  appointments: any[]

  isLoading = false;

  refresh = new Subject<void>();

  events: CalendarEvent[];

  constructor(private modalService: NgbModal,
    private appointmentService: AppointmentService,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.events = []
    this.appointmentService.notPassedAppointments(this.authService.getUser())
      .subscribe((response: any) => {
        this.appointments = response;
        this.createEvents();
      })
  }

  handleEvent(event: CalendarEvent): void {
    const modalRef = this.modalService.open(ScheduledAppointmentDetailComponent, { centered: true });
    modalRef.componentInstance.event = event;

  }

  createEvents() {
    this.appointments.forEach(appointment => {
      let event: CalendarEvent = {
        id: appointment.id,
        start: this.parseDateString(appointment.startDateTime),
        end: addMinutes(this.parseDateString(appointment.startDateTime), 30),
        title: appointment.center.name,
        color: { ...colors['red'] },
        meta: {
          centerName: appointment.center.name,
          centerAddress: appointment.center.address + ", " + appointment.center.city + ", " + appointment.center.country
        }

      }
      this.events.push(event)
    
    })
    this.isLoading = true;
  
  }


  setView(view: CalendarView) {
    this.view = view;
  }

  private parseDateString(date: any): Date {
    date = date.replace('T', '-');
    var parts = date.split('-');
    var timeParts = parts[3].split(':');
    return new Date(parts[0], parts[1] - 1, parts[2], timeParts[0], timeParts[1]);
  }




}
