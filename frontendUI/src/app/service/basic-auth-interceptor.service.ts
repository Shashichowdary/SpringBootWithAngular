import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class BasicAuthInterceptorService implements HttpInterceptor{

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const userName = 'admin';
    const pasword = 'password';
    const basicAuthHeader = 'Basic ' + btoa(userName + ':' +pasword);

    const authReq = req.clone({
      setHeaders:{
        Authorization: basicAuthHeader
      }
    });
    return next.handle(authReq);
  }
}
