import { Injectable } from '@angular/core';
import { UserObject } from '../../utils/interfaces';
import { HttpClient } from '@angular/common/http';
import { request } from 'http';
import { BehaviorSubject, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserHandlerService {
  public user: BehaviorSubject<UserObject> = new BehaviorSubject<UserObject>({
    "token": "",
    "id": "",
    "type": "",
    "username": "",
    "email": "",
    "roles": [""],
  });
  private baseUrl: string = "https://localhost:9081/auth/"

  constructor(private http: HttpClient) {  }

   attemptLogin(username: string, password: string): void {
    console.log("attempting login")
    const body = {
      "username": username,
      "password": password,
    }

    const requestOptions = {
      headers: {
        "Content-type": "application/json",
        "Referrer-Policy": "no-referrer",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Credentials": "true",
        "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS",
      }
    }

    this.http.post<UserObject>(this.baseUrl+"signin", body, requestOptions)
      .subscribe({
        next: (data) => {
          console.log("new user data: ", data);
          this.user.next(data);
        }
      })
   }

   attemptSignup(): void {

   }

}
