import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

var skipAuth = ""

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log("Ok")
    if(this.authService.getToken() != null && this.authService.tokenExpired(this.authService.getToken())){
      console.log("Token expired, user will be logged out")
      localStorage.removeItem("token")
      this.authService.logout()
    }
      

    if(this.authService.getToken() == null)
        skipAuth = ""
    else skipAuth = "Bearer "+ this.authService.getToken()
    
    const modifiedReq = request.clone({ 
        headers: request.headers
        .set("Authorization", skipAuth)  
      });
  
    return next.handle(modifiedReq)
    .pipe();
  
  }
}
