// auth.service.ts

import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isAuthenticated = new BehaviorSubject<boolean>(false);
  public isAuthenticated$ = this.isAuthenticated.asObservable();

  private loggedInUser = new BehaviorSubject<string>('');
  public loggedInUser$ = this.loggedInUser.asObservable();

  constructor() {}

  login(username: string): void {
    this.isAuthenticated.next(true);
    this.loggedInUser.next(username);
  }

  logout(): void {
    this.isAuthenticated.next(false);
    this.loggedInUser.next('');
  }
}