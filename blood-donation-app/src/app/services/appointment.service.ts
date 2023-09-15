import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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

  public schedule(data: any){
    return this.http.post(this.baseURL + "/schedule", data, this.getAuthoHeader());
  }

  public getNotPassedDonorAppointments(donorUsername: any){
    return this.http.get(this.baseURL + "/notPassed/" + donorUsername, this.getAuthoHeader());
  }

  public cancel(id: any){
    return this.http.delete(this.baseURL + "/" + id, this.getAuthoHeader());
  }

  public getDonorAppointments(donorUsername: any){
    return this.http.get(this.baseURL + "/donor/" + donorUsername, this.getAuthoHeader());
  }

  public getBloodHistoryForManager(managerUsername: any){
    return this.http.get(this.baseURL + "/manager/" + managerUsername, this.getAuthoHeader());
  }

  public sortDonorAppointments(data: any){
    let params = new HttpParams();
    params = params.append("sortBy", data.sortBy).append("sortDirection", data.sortDirection)
    .append("donorUsername", data.donorUsername)
    .append("searchText", data.searchText)
    .append("searchDate", data.searchDate);
    const headers = new HttpHeaders().set('Authorization' , 'Bearer ' + localStorage.getItem("token"));
    const options = { params: params, headers: headers };
    return this.http.get(this.baseURL + "/sort", options);
  }

}
