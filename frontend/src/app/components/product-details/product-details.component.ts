import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../services/product.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {Product} from "../../interfaces/product";
import {CurrencyPipe, NgIf} from "@angular/common";
import {CartService} from "../../services/cart.service";
import {CartItem} from "../../interfaces/cart-item";
import {environment} from "../../../enviroments/enviroment";

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [
    CurrencyPipe,
    RouterLink,
    NgIf
  ],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit {

  // product: Product = new Product();
  product!: Product;

  constructor(private productService: ProductService,
              private cartService: CartService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.handleProductDetails();
    })
  }

  handleProductDetails() {
    // get the "id" param string. convert string to a number using the "+" symbol
    const productName: string = this.route.snapshot.paramMap.get('name')!;
    if(productName) {
      this.productService.getProductByName(productName).subscribe(
        data => {
          this.product = data;
        }
      )
    }
  }

  addToCart() {
    const cartItem = {
      product: this.product,
      quantity: 1
    };
    this.cartService.addToCartOrIncrementQuantity(cartItem);
  }

  protected readonly environment = environment;

  getImage(product: Product): string {
    if (product.imageUrl) {
      return 'data:image/png;base64,' + product.imageUrl
    }
    return 'https://upload.wikimedia.org/wikipedia/en/3/30/Java_programming_language_logo.svg';
  }
}
