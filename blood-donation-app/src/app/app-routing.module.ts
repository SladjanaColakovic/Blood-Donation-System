import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './homepage/home-page/home-page.component';
import { AuthGuard } from './authentication/auth.guard';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  { path: '', component: HomePageComponent, canActivate: [AuthGuard],  data: {role: 'USER'} },
  { path: 'login', component: LoginComponent},
  { path: 'registration', component: RegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
