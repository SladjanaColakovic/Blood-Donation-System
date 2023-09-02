import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './homepage/home-page/home-page.component';
import { AuthGuard } from './authentication/auth.guard';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { CenterRegistrationComponent } from './center-registration/center-registration.component';
import { CentersComponent } from './centers/centers.component';
import { CenterDetailComponent } from './center-detail/center-detail.component';

const routes: Routes = [
  { path: '', component: HomePageComponent, canActivate: [AuthGuard], data: { role: 'USER' } },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'centers', component: CentersComponent },
  { path: 'center/:id', component: CenterDetailComponent },
  { path: 'centerRegistration', component: CenterRegistrationComponent, canActivate: [AuthGuard], data: { role: 'ADMIN' } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
