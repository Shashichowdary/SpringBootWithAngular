import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { EditDetailsComponent } from './edit-details/edit-details.component';
import { DashBoardComponent } from './dash-board/dash-board.component';
import { OrderComponent } from './order/order.component';
import { OrderDashBoardComponent } from './order-dash-board/order-dash-board.component';
import { EditOrdersComponent } from './edit-orders/edit-orders.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MyOrderComponent } from './my-order/my-order.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'dashBoard', component: DashBoardComponent },
  { path: 'edit-details/:userName', component: EditDetailsComponent},
  { path: 'orders', component: OrderComponent},
  { path: 'orderDashBoard', component: OrderDashBoardComponent},
  { path: 'edit-orders/:orderId', component: EditOrdersComponent},
  { path: 'myOrders', component: MyOrderComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}