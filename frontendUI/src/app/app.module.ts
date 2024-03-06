import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SignupComponent } from './signup/signup.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { EditDetailsComponent } from './edit-details/edit-details.component';
import { DashBoardComponent } from './dash-board/dash-board.component';
import { OrderComponent } from './order/order.component';
import { OrderDashBoardComponent } from './order-dash-board/order-dash-board.component';
import { BasicAuthInterceptorService } from './service/basic-auth-interceptor.service';
import { EditOrdersComponent } from './edit-orders/edit-orders.component';
import { CommonModule } from '@angular/common';
import { MyOrderComponent } from './my-order/my-order.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    SignupComponent,
    ResetPasswordComponent,
    DashBoardComponent,
    EditDetailsComponent,
    OrderComponent,
    OrderDashBoardComponent,
    EditOrdersComponent,
    MyOrderComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: BasicAuthInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
