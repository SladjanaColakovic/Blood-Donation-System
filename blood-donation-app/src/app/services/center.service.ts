import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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

  public register(data: any){
    return this.http.post(this.baseURL + "/add", data, this.getAuthoHeader());
  }

  public changeImage(data: FormData){
    return this.http.put(this.baseURL + "/image", data, this.getAuthoHeader());
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

  public getFreeCenters(dateTime: any, center: any, address: any, sortBy: any, sortDirection: any){
    let params = new HttpParams();
    params = params
    .append("dateTime", dateTime)
    .append("center", center)
    .append("address", address)
    .append("sortBy", sortBy)
    .append("sortDirection", sortDirection);
    const options = { params: params};
    return this.http.get(this.baseURL + "/searchSort", options);
  }
 
}
