import { Injectable } from '@angular/core';

import { Order } from './Order';
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
export class OrderServiceService {
  REST_API: string ='http://localhost:8080/ordersService/api';

  htpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private httpClient : HttpClient) { }

  //createOrder
  createOrder(data : Order): Observable<any> {
    let API_URL = `${this.REST_API}/createOrder`;
    return this.httpClient
    .post(API_URL, data, {withCredentials: true})
    .pipe(catchError(this.handleError));
  }

  //getAllOrders
  GetOrders() {
    return this.httpClient.get(`${this.REST_API}/getAllOrders`);
  }

  //getAllOrdersByUserName
  GetOrdersByUserName() {
    return this.httpClient.get(`${this.REST_API}/getMyOrders`, {withCredentials: true});
  }

  UpdateOrder(orderId: any, data : Order): Observable<any> {
    let API_URL = `${this.REST_API}/updateOrder/${orderId}`;
    return this.httpClient
    .put(API_URL, data)
    .pipe(catchError(this.handleError));
  }

  DeleteOrder(orderId: number): Observable<any> {
    let API_URL = `${this.REST_API}/deleteOrder/${orderId}`;
    return this.httpClient
    .delete(API_URL)
    .pipe(catchError(this.handleError));
  }

  GetOrderById(orderId: any): Observable<any> {
    let API_URL = `${this.REST_API}/getOrderById/${orderId}`;
    return this.httpClient.get(API_URL, {headers: this.htpHeaders}).pipe(
      map((res: any) => {
        return res || {};
      }),
      catchError(this.handleError)
    );
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

