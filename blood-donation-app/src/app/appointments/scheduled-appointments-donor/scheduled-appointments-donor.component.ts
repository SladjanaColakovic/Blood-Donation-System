import { Component } from '@angular/core';
import { CalendarEvent } from 'angular-calendar';
import { addMinutes } from 'date-fns';
import { EventColor } from 'calendar-utils';
import { AppointmentService } from '../../services/appointment.service';
import { AuthService } from '../../authentication/auth.service';
import { Router } from '@angular/router';

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

  appointments: any[]
  isLoading = false;
  events: CalendarEvent[];

  constructor(private appointmentService: AppointmentService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.events = []
    this.appointmentService.getNotPassedDonorAppointments(this.authService.getUser())
      .subscribe((response: any) => {
        this.appointments = response;
        this.createEvents();
      }, error => {
        console.log(error)
      })
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
          centerAddress: appointment.center.address + ", " + appointment.center.city + ", " + appointment.center.country,
        }
      }
      this.events.push(event)
    })
    this.isLoading = true;
  }

  private parseDateString(date: any): Date {
    date = date.replace('T', '-');
    var parts = date.split('-');
    var timeParts = parts[3].split(':');
    return new Date(parts[0], parts[1] - 1, parts[2], timeParts[0], timeParts[1]);
  }

}
