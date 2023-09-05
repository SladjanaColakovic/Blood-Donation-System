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

  notPassedAppointments(donorUsername: any){
    return this.http.get(this.baseURL + "/notPassed/" + donorUsername, this.getAuthoHeader());
  }

  cancel(id: any){
    return this.http.delete(this.baseURL + "/" + id, this.getAuthoHeader());
  }

  getDonorAppointments(donorUsername: any){
    return this.http.get(this.baseURL + "/donor/" + donorUsername, this.getAuthoHeader());
  }

  getBloodCenterApppointments(managerUsername: any){
    return this.http.get(this.baseURL + "/manager/" + managerUsername, this.getAuthoHeader());
  }

  
}
