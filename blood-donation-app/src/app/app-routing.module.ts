import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './homepage/home-page/home-page.component';
import { AuthGuard } from './authentication/auth.guard';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { CenterRegistrationComponent } from './center-registration/center-registration.component';
import { CentersComponent } from './centers/centers.component';
import { CenterDetailComponent } from './center-detail/center-detail.component';
import { EditCenterInfoComponent } from './edit-center-info/edit-center-info.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ScheduledAppointmentsDonorComponent } from './scheduled-appointments-donor/scheduled-appointments-donor.component';
import { BloodDonationHistoryComponent } from './blood-donation-history/blood-donation-history.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'profile', component: UserProfileComponent, canActivate: [AuthGuard], data: { role: 'USER' } },
  { path: 'centers', component: CentersComponent },
  { path: 'center/:id', component: CenterDetailComponent },
  { path: 'centerRegistration', component: CenterRegistrationComponent, canActivate: [AuthGuard], data: { role: 'ADMIN' } },
  { path: 'editCenter', component: EditCenterInfoComponent, canActivate: [AuthGuard], data: { role: 'MANAGER' } },
  { path: 'scheduledAppointments', component: ScheduledAppointmentsDonorComponent, canActivate: [AuthGuard], data: { role: 'USER' } },
  { path: 'history', component: BloodDonationHistoryComponent, canActivate: [AuthGuard], data: { role: 'USER' } },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
