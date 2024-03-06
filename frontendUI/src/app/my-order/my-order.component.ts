import { Component, NgZone, OnInit } from '@angular/core';
import { OrderServiceService } from '../service/order.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-my-order',
  templateUrl: './my-order.component.html',
  styleUrls: ['./my-order.component.scss']
})
export class MyOrderComponent implements OnInit {

  Order: any = [];
  searchTerm: string = '';
  itemsPerPage = 2;
  currentPage = 1;
  totalItems = 0;
  pages: number[] = [];

  constructor(private orderService: OrderServiceService,
    private router : Router,
    private ngZone : NgZone,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.orderService.GetOrdersByUserName().subscribe((res: any) => {
      console.log(res);
      this.Order = res;

      // Calculate total items dynamically based on the length of the Order array
      this.totalItems = this.Order.length;

      // Update the pages array based on the totalItems
      this.updatePagesArray();
    });
  }

  deleteUser(orderId: number): void {
    alert("Are you sure, you want to delete the order placed?");
    console.log("deletion of order with orderId", orderId);
    this.orderService.DeleteOrder(orderId).subscribe((res: any) => {
      ()=>this.ngZone.run(() => this.router.navigateByUrl('/orderDashBoard'));
      console.log(res);
    })
  }

  get filteredOrders() {
    // Assuming you want to paginate the filtered results
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.Order.slice(startIndex, endIndex).filter((order: any) =>
      this.doesOrderMatchSearch(order, this.searchTerm)
    );
  }

  // private doesOrderMatchSearch(order: any, searchTerm: string): boolean {
  //   // Check each property of the order against the search term
  //   for (const key in order) {
  //     if (Object.prototype.hasOwnProperty.call(order, key)) {
  //       const value = order[key];

  //       if (value && typeof value === 'string' && value.toLowerCase().includes(searchTerm.toLowerCase())) {
  //         return true;
  //       }
  //     }
  //   }
  //   return false;
  // }

  private doesOrderMatchSearch(order: any, searchTerm: string): boolean {
    for (const key in order) {
      if (Object.prototype.hasOwnProperty.call(order, key)) {
        const value = order[key];

        if (
          value &&
          typeof value === 'string' &&
          value.toLowerCase().includes(searchTerm.toLowerCase())
        ) {
          return true;
        }
      }
    }
    return false;
  }

  changePage(page: number): void {
    this.currentPage = page;
  }

  private updatePagesArray(): void {
    // Calculate the total number of pages based on itemsPerPage
    const totalPages = Math.ceil(this.totalItems / this.itemsPerPage);

    // Generate an array from 1 to totalPages
    this.pages = Array.from({ length: totalPages }, (_, index) => index + 1);
  }

}
