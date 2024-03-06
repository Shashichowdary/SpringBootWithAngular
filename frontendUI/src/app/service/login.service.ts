import { Injectable } from '@angular/core';
import { User } from './User';
import { catchError, map } from 'rxjs/operators';
import { Observable, from, throwError } from 'rxjs';
import{
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http'
import { LoginUser } from './LoginUser';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  REST_API: string ='http://localhost:8080/loginService/api';

  htpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private httpClient : HttpClient) { }

  //login
  Login(data : LoginUser): Observable<any> {
    let API_URL = `${this.REST_API}/login`;
    return this.httpClient
    .post(API_URL, data, { withCredentials : true })
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
