import { Component,NgZone, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderServiceService } from '../service/order.service';

@Component({
  selector: 'app-edit-orders',
  templateUrl: './edit-orders.component.html',
  styleUrls: ['./edit-orders.component.scss']
})
export class EditOrdersComponent implements OnInit {
  getOrderId : any;
  editForm!: FormGroup;

  constructor(
    public fb: FormBuilder,
    private router : Router,
    private ngZone : NgZone,
    private activatedRoute: ActivatedRoute,
    private orderService : OrderServiceService
  ) { 
    this.getOrderId = this.activatedRoute.snapshot.paramMap.get('orderId');
    this.orderService.GetOrderById(this.getOrderId).subscribe((res: { [x: string]: any}) => {
          console.log("response" + res)
          this.editForm.setValue({
            itemName: res['itemName'],
            price: res['price'],
            quantity: res['quantity']
          });
        });
  }

  ngOnInit() {
    this.editForm = this.fb.group({
      itemName: ['', Validators.required],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
  });


}
editOnSubmit(): any {
  this.orderService.UpdateOrder(this.getOrderId, this.editForm.value).subscribe(
    () => {
      console.log('orders Updated Successfully!');
      this.ngZone.run(() => this.router.navigateByUrl('/orderDashBoard'));
    },
    (err) => {
      console.log(err);
    }
  );
}
get itemName() {
  return this.editForm.get('itemName');
}

get price() {
  return this.editForm.get('price');
}

get quantity() {
  return this.editForm.get('quantity');
}
}
