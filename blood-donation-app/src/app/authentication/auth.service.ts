import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseURL = "http://localhost:8080/auth"

  constructor(private http: HttpClient, private router: Router) { }

  public isLoggedIn() {
    return !!localStorage.getItem('token');
  }

  public getRole() {
    let token = localStorage.getItem('token');
    if (token) {
      var obj = JSON.parse(window.atob(token.split('.')[1]));
      return obj.role;
    }
    return '';
  }

  public getUser(){
    let token = localStorage.getItem('token');
    if(token){
      var obj = JSON.parse(window.atob(token.split('.')[1]));
      return obj.sub;
    }
    return null;
  }

  public login(data: any) {
    return this.http.post(this.baseURL + "/login", data);
  }

  public logout() {
    localStorage.removeItem("token");
    this.router.navigate(['/login']);
  }

  public tokenExpired(token: any) {
    const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
    return (Math.floor((new Date).getTime() / 1000)) >= expiry;
  }

  public getToken(){
    return localStorage.getItem('token');
  }

}
