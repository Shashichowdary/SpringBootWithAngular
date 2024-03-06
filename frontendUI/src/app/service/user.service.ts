import { Injectable } from '@angular/core';
import { User } from './User';
import { catchError, map } from 'rxjs/operators';
import { Observable, from, throwError } from 'rxjs';
import{
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class UserService {
  REST_API: string ='http://localhost:8080/userService/api';

  htpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private httpClient : HttpClient) { }

  //createNewUser
  CreateNewUser(data : User): Observable<any> {
    let API_URL = `${this.REST_API}/createNewUser`;
    return this.httpClient
    .post(API_URL, data)
    .pipe(catchError(this.handleError));
  }

  //updateUser
  UpdateUser(userId: any, data : User): Observable<any> {
    let API_URL = `${this.REST_API}/updateUser/${userId}`;
    return this.httpClient
    .put(API_URL, data)
    .pipe(catchError(this.handleError));
  }

  //updateUserByUserName
  UpdateUserByUserName(userName: any, data : User): Observable<any> {
    let API_URL = `${this.REST_API}/updateUser/${userName}`;
    return this.httpClient
    .put(API_URL, data)
    .pipe(catchError(this.handleError));
  }

  //getAllUsers
  GetUsers() {
    return this.httpClient.get(`${this.REST_API}/getRegisteredUsers`);
  }

  //GetUser
  GetUser(userId: any): Observable<any> {
    let API_URL = `${this.REST_API}/getUserById/${userId}`;
    return this.httpClient.get(API_URL, {headers: this.htpHeaders}).pipe(
      map((res: any) => {
        return res || {};
      }),
      catchError(this.handleError)
    );
  }

  //GetUserByUsername
  GetUserByUserName(userName: any): Observable<any> {
    let API_URL  = `${this.REST_API}/getUserByUserName/${userName}`;
    return this.httpClient.get(API_URL, {headers: this.htpHeaders}).pipe(
      map((res: any) => {
        return res || {};
      }),
      catchError(this.handleError)
    );
  }

  GetSecurityQuestions(userName: any): Observable<any> {
    let API_URL  = `${this.REST_API}/getSecurityQuestions/${userName}`;
    return this.httpClient.get(API_URL, {headers: this.htpHeaders}).pipe(
      map((res: any) => {
        return res || {};
      }),
      catchError(this.handleError)
    );
  }

  ResetPassword(data : User): Observable<any> {
    let API_URL = `${this.REST_API}/resetPassword`;
    return this.httpClient
    .post(API_URL, data)
    .pipe(catchError(this.handleError));
  }

  //Error
  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent){
      //client error
      errorMessage = error.error.message;
    }else{
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(() => {
      errorMessage;
    });
  }
}
