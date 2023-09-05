import { Component } from '@angular/core';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { Subject } from 'rxjs';
import { AppointmentService } from '../services/appointment.service';
import { AuthService } from '../authentication/auth.service';
import { addMinutes } from 'date-fns';
import { EventColor } from 'calendar-utils';

const colors: Record<string, EventColor> = {
  red: {
    primary: '#d17272',
    secondary: '#FAE3E3',
  },
  yellow: {
    primary: '#ffee4f',
    secondary: '#fff49b'
  }
};

@Component({
  selector: 'app-blood-center-appointment-history',
  templateUrl: './blood-center-appointment-history.component.html',
  styleUrls: ['./blood-center-appointment-history.component.css']
})
export class BloodCenterAppointmentHistoryComponent {

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  appointments: any[]

  isLoading = false;

  refresh = new Subject<void>();

  events: CalendarEvent[];

  constructor(
    private appointmentService: AppointmentService,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.events = []
    this.appointmentService.getBloodCenterApppointments(this.authService.getUser()).
      subscribe((response: any) => {
        this.appointments = response;
        this.createEvents();
      })
  }

  handleEvent(event: CalendarEvent): void {
    /*const modalRef = this.modalService.open(ScheduledAppointmentDetailComponent, { centered: true });
    modalRef.componentInstance.event = event;*/
  }

  createEvents() {
    this.appointments.forEach(appointment => {
      let event: CalendarEvent = {
        id: appointment.id,
        start: this.parseDateString(appointment.startDateTime),
        end: addMinutes(this.parseDateString(appointment.startDateTime), 30),
        title: appointment.donor.name + " " + appointment.donor.surname,
        color: (addMinutes(this.parseDateString(appointment.startDateTime), 30) > new Date())? { ...colors['red'] } : { ...colors['yellow'] },
        meta: {
          
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
