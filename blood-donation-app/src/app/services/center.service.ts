import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  baseURL = "http://localhost:8080/api/center"

  getAuthoHeader() : any {
    const headers = {
      'Authorization' : 'Bearer ' + localStorage.getItem("token")
    }
    return{
      headers: headers
    };
  }  

  constructor(private http: HttpClient) { }

  public register(data: FormData){
    return this.http.post(this.baseURL + "/add", data,  this.getAuthoHeader())
  }

  public getAll(){
    return this.http.get(this.baseURL + "/all");
  }

  public getById(id: any){
    return this.http.get(this.baseURL + "/info/" + id);
  }

  public getManagerCenter(username: any){
    return this.http.get(this.baseURL + "/" + username, this.getAuthoHeader());
  }

  public editCenterInfo(data: any){
    return this.http.put(this.baseURL + "/edit", data, this.getAuthoHeader());
  }

  public getFreeCenters(dateTime: any){
    return this.http.get(this.baseURL + "/free/" + dateTime, this.getAuthoHeader());
  }
 
}
