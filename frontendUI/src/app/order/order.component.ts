import { Component,NgZone, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OrderServiceService } from '../service/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
[x: string]: any;

orderForm!: FormGroup;

  constructor(public fb: FormBuilder,
    private router : Router,
    private ngZone : NgZone,
    private orderServiceService : OrderServiceService
    ) {
      this.orderForm = this.fb.group({
        itemName: [''],
        price: [''],
        quantity: [''],
      })
    }

    ngOnInit() {
      this.orderForm = this.fb.group({
        itemName: ['', Validators.required],
        price: ['', Validators.required],
        quantity: ['', Validators.required],
            })
          }
  
  
          submitOrder(): any {
  this.orderServiceService.createOrder(this.orderForm.value).subscribe(
    () => {
      console.log('Data Added Successfully!');
      this.ngZone.run(() => this.router.navigateByUrl('/orderDashBoard'));
    },
    
  );

}

clearForm() {
  this.orderForm.reset();
}

get itemName() {
  return this.orderForm.get('itemName');
}

get price() {
  return this.orderForm.get('price');
}

get quantity() {
  return this.orderForm.get('quantity');
}
}


