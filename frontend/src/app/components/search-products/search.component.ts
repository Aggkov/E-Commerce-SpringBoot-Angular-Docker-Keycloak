import {Component, HostListener, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {debounceTime, distinctUntilChanged, Observable, of, switchMap, tap} from "rxjs";
import {Product} from "../../model/product";
import {ProductService} from "../../services/product.service";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {CartStatusComponent} from "../cart-status/cart-status.component";
import {KeycloakService} from "../../services/keycloak/keycloak.service";

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    ReactiveFormsModule,
    NgForOf,
    AsyncPipe,
    CartStatusComponent,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {
  searchForm: FormGroup;
  searchQuery: string = '';
  suggestions$: Observable<Product[]> = of([]);
  dropdownVisible = false;
  isAdmin: boolean = false;
  isUser: boolean = false;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router,
    private keycloakService: KeycloakService) {
    this.searchForm = this.fb.group({
      searchQuery: ['']
    });
    this.keycloakService.init().then(() => {
      this.isAdmin = this.keycloakService.hasRole('admin');
      this.isUser = this.keycloakService.hasRole('user');
    });
  }

  ngOnInit(): void {
    this.suggestions$ = this.searchForm.get('searchQuery')!.valueChanges
      .pipe(
        debounceTime(200),
        distinctUntilChanged(),
        switchMap(query => {
          if (query.trim() === "") {
            this.dropdownVisible = false; // Hide dropdown if query is empty
            // Return an observable of an empty array if the query is empty
            return of([]);
          } else {
            this.dropdownVisible = true; // Show dropdown if there is a query
            return this.productService.searchProductByKeywords(query);
          }
        })
      );
  }

  onSearch(): void {
    const query = this.searchForm.get('searchQuery')!.value;
    this.router.navigate(['/search', query]);
  }

  selectSuggestion(suggestion: Product): void {
    this.searchForm.get('searchQuery')!.setValue(suggestion.name);
    this.dropdownVisible = false; // Hide dropdown after selection
    // this.suggestions$ = of([]); // Clear suggestions after selection
    this.onSearch(); // Optionally perform search on suggestion select
  }

  @HostListener('document:click', ['$event'])
  onClick(event: Event): void {
    const target = event.target as HTMLElement;
    if (!target.closest('.ul')) {
      this.dropdownVisible = false; // Hide dropdown if click is outside
    }
  }

  keycloakLogin() {
    // this.keycloakService.init().then(() => {
    return this.keycloakService.login();
  }

  keycloakLogout() {
    return this.keycloakService.logout();
  }

  navigateToAdminPanel() {
    this.router.navigate(['/admin-panel']);
    // this.router.navigateByUrl('/admin-panel');
  }

  navigateToUserOrders() {
    this.router.navigate(['/order-list']);
  }
}
