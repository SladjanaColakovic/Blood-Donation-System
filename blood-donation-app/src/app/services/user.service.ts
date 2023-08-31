import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseURL = "http://localhost:8080/api/user"

  constructor(private http: HttpClient) { }

  public register(data: any){
    return this.http.post(this.baseURL + "/register", data);
  }
}
