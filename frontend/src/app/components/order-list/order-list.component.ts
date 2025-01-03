import {Component, OnInit, ViewChild} from '@angular/core';
import {Order} from "../../interfaces/order/order";
import {OrderService} from "../../services/order.service";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {Router} from "@angular/router";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [
    MatPaginator,
    NgForOf
  ],
  templateUrl: './order-list.component.html',
  styleUrl: './order-list.component.css'
})
export class OrderListComponent implements OnInit {

  orders: Order[] = [];
  pageNumber: number = 0;
  pageSize: number = 10;
  totalElements: number = 0;
  pageSizes: number[] = [2, 5, 10, 20, 50];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private orderService: OrderService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.listOrders();
  }

  private listOrders() {
    this.orderService.getOrdersByUser(this.pageNumber, this.pageSize)
      .subscribe({
      next: response => {
        this.orders = response.content;
        this.pageNumber = response.page;
        this.pageSize = response.size;
        this.totalElements = response.totalElements;
        this.paginator.length = response.totalElements;  // Update paginator with total products
      },
        error: err => console.log(err)
    });
  }

  viewOrderDetails(orderTrackingNumber: string) {
    this.router.navigate(['/order-details', orderTrackingNumber]);
  }

  onPageChange(event: PageEvent) {
    if(event.pageSize !== this.pageSize && this.pageNumber !== 0) {
      this.pageNumber = 0;
      this.pageSize = event.pageSize;
      this.listOrders();
    }
    else {
      this.pageNumber = event.pageIndex;   // Update current page index
      this.pageSize = event.pageSize;      // Update current page size
      // Fetch data with updated page info
      this.listOrders();
    }
  }
}
