import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './homepage/home-page/home-page.component';
import { AuthGuard } from './authentication/auth.guard';
import { LoginComponent } from './user/login/login.component';
import { RegistrationComponent } from './user/registration/registration.component';
import { CenterRegistrationComponent } from './centers/center-registration/center-registration.component';
import { CentersReviewComponent } from './centers/centers-review/centers-review.component';
import { CenterDetailComponent } from './centers/center-detail/center-detail.component';
import { CenterProfileComponent } from './centers/center-profile/center-profile.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { ScheduledAppointmentsDonorComponent } from './appointments/scheduled-appointments-donor/scheduled-appointments-donor.component';
import { BloodDonationHistoryDonorComponent } from './appointments/blood-donation-history-donor/blood-donation-history-donor.component';
import { BloodDonationHistoryManagerComponent } from './appointments/blood-donation-history-manager/blood-donation-history-manager.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'profile', component: UserProfileComponent, canActivate: [AuthGuard], data: { role: 'DONOR|ADMIN|MANAGER' } },
  { path: 'centers', component: CentersReviewComponent },
  { path: 'center/:id', component: CenterDetailComponent },
  { path: 'centerRegistration', component: CenterRegistrationComponent, canActivate: [AuthGuard], data: { role: 'ADMIN' } },
  { path: 'editCenter', component: CenterProfileComponent, canActivate: [AuthGuard], data: { role: 'MANAGER' } },
  { path: 'scheduledAppointments', component: ScheduledAppointmentsDonorComponent, canActivate: [AuthGuard], data: { role: 'DONOR' } },
  { path: 'history', component: BloodDonationHistoryDonorComponent, canActivate: [AuthGuard], data: { role: 'DONOR' } },
  { path: 'appointmentsHistory', component: BloodDonationHistoryManagerComponent, canActivate: [AuthGuard], data: { role: 'MANAGER' } }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
