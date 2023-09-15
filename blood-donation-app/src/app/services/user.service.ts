import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseURL = "http://localhost:8080/api/user"

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

  public register(data: any){
    return this.http.post(this.baseURL + "/register", data);
  }

  public current(username: any){
    return this.http.get(this.baseURL + "/current/" + username, this.getAuthoHeader())
  }

  public edit(data: any){
    return this.http.put(this.baseURL + "/edit", data, this.getAuthoHeader());
  }
  
}
