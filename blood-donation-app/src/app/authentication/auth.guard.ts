import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (this.authService.isLoggedIn()) {

      let role = route.data['role'];
      if (role == this.authService.getRole()) {
        if (!this.authService.tokenExpired(this.authService.getToken())) {
          return true;
        }
        localStorage.removeItem('token')
        window.location.href = "http://localhost:4200/login"
        return false;

      }
      if (role.includes("|")) {
        if (!this.authService.tokenExpired(this.authService.getToken())) {
          return true;
        }
        localStorage.removeItem('token')
        window.location.href = "http://localhost:4200/login"
        return false;
      }

      // window.location.href="http://localhost:4200/login"
      // return false;
    }

    return false;
  }

}
