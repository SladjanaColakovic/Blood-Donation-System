import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { FooterComponent } from './footer/footer.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { HomePageComponent } from './homepage/home-page/home-page.component';
import { BloodDonationInformationComponent } from './homepage/blood-donation-information/blood-donation-information.component';
import { BloodDonationGaleryComponent } from './homepage/blood-donation-galery/blood-donation-galery.component';
import { LoginComponent } from './user/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegistrationComponent } from './user/registration/registration.component';
import { ErrorAlertComponent } from './error-alert/error-alert.component';
import { CenterRegistrationComponent } from './centers/center-registration/center-registration.component';
import { CentersReviewComponent } from './centers/centers-review/centers-review.component';
import { CenterDetailComponent } from './centers/center-detail/center-detail.component';
import { CenterProfileComponent } from './centers/center-profile/center-profile.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { ScheduledAppointmentsDonorComponent } from './appointments/scheduled-appointments-donor/scheduled-appointments-donor.component';
import { ScheduledAppointmentDetailComponent } from './appointments/scheduled-appointment-detail/scheduled-appointment-detail.component';
import { BloodDonationHistoryDonorComponent } from './appointments/blood-donation-history-donor/blood-donation-history-donor.component';
import { BloodDonationHistoryManagerComponent } from './appointments/blood-donation-history-manager/blood-donation-history-manager.component';
import { AppointmentsCalendarComponent } from './appointments/appointments-calendar/appointments-calendar.component';
import { UserInfoEditComponent } from './user/user-info-edit/user-info-edit.component';
import { UserPasswordEditComponent } from './user/user-password-edit/user-password-edit.component';
import { CenterInfoEditComponent } from './centers/center-info-edit/center-info-edit.component';
import { CenterSearchSortComponent } from './centers/center-search-sort/center-search-sort.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    NavBarComponent,
    HomePageComponent,
    BloodDonationInformationComponent,
    BloodDonationGaleryComponent,
    LoginComponent,
    RegistrationComponent,
    ErrorAlertComponent,
    CenterRegistrationComponent,
    CentersReviewComponent,
    CenterDetailComponent,
    CenterProfileComponent,
    UserProfileComponent,
    ScheduledAppointmentsDonorComponent,
    ScheduledAppointmentDetailComponent,
    BloodDonationHistoryDonorComponent,
    BloodDonationHistoryManagerComponent,
    AppointmentsCalendarComponent,
    UserInfoEditComponent,
    UserPasswordEditComponent,
    CenterInfoEditComponent,
    CenterSearchSortComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
