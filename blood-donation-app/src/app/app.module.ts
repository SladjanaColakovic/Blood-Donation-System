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
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { RegistrationComponent } from './registration/registration.component';
import { ErrorAlertComponent } from './error-alert/error-alert.component';
import { CenterRegistrationComponent } from './center-registration/center-registration.component';
import { CentersComponent } from './centers/centers.component';
import { CenterDetailComponent } from './center-detail/center-detail.component';
import { EditCenterInfoComponent } from './edit-center-info/edit-center-info.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ScheduledAppointmentsDonorComponent } from './scheduled-appointments-donor/scheduled-appointments-donor.component';
import { ScheduledAppointmentDetailComponent } from './scheduled-appointment-detail/scheduled-appointment-detail.component';




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
    CentersComponent,
    CenterDetailComponent,
    EditCenterInfoComponent,
    UserProfileComponent,
    ScheduledAppointmentsDonorComponent,
    ScheduledAppointmentDetailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
