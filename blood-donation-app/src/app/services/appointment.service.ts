import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  baseURL = "http://localhost:8080/api/appointment"

  getAuthoHeader() : any {
    const headers = {
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("token")
    }
    return{
      headers: headers
    };
  } 

  constructor(private http: HttpClient) { }

  schedule(data: any){
    return this.http.post(this.baseURL + "/schedule", data, this.getAuthoHeader());
  }
}
