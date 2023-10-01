import { Component } from '@angular/core';
import { CalendarEvent } from 'angular-calendar';
import { AppointmentService } from '../../services/appointment.service';
import { AuthService } from '../../authentication/auth.service';
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
  selector: 'app-blood-history-manager',
  templateUrl: './blood-donation-history-manager.component.html',
  styleUrls: ['./blood-donation-history-manager.component.css']
})
export class BloodDonationHistoryManagerComponent {

  appointments: any[]
  isLoading = false;
  events: CalendarEvent[];

  constructor(private appointmentService: AppointmentService, private authService: AuthService) {}

  ngOnInit() {
    this.events = []
    this.appointmentService.getBloodHistoryForManager(this.authService.getUser()).
      subscribe((response: any) => {
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
        title: appointment.donor.name + " " + appointment.donor.surname,
        color: (addMinutes(this.parseDateString(appointment.startDateTime), 30) > new Date()) ? { ...colors['red'] } : { ...colors['yellow'] },
        meta: {
          donor: appointment.donor.name + " " + appointment.donor.surname
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
