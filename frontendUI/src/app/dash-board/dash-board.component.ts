import { Component, NgZone, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.scss'],
})
export class DashBoardComponent implements OnInit {
  Users: any = [];
  searchTerm: string = '';
  isLoggedIn = false;
  loggedInUser = '';
  itemsPerPage = 3;
  currentPage = 1;
  totalItems = 0;
  pages: number[] = [];

  editProfile(): void {
    this.userService.GetUserByUserName(this.loggedInUser).subscribe(
      (response) => {
        console.log('API response:', response);
      },
      (error) => {
        console.error('API error:', error);
      }
    );
  }

  logout() {
    this.deleteCookie('username');
    this.authService.logout();
    this.ngZone.run(() => this.router.navigateByUrl('/login'));
  }

  private deleteCookie(cookieName: string): void {
    document.cookie = `${cookieName}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
  }

  constructor(private userService: UserService, private authService: AuthService, private router : Router,
    private ngZone : NgZone,) {}
    ngOnInit(): void {
      this.authService.isAuthenticated$.subscribe((isAuthenticated) => {
        this.isLoggedIn = isAuthenticated;
      });
  
      this.authService.loggedInUser$.subscribe((user) => {
        this.loggedInUser = user;
      });
  
      this.userService.GetUsers().subscribe((res: any) => {
        console.log(res);
        this.Users = res;
      // Calculate total items dynamically based on the length of the Order array
      this.totalItems = this.Users.length;
  
      // Update the pages array based on the totalItems
      this.updatePagesArray();
      });
    }
  // get filteredUsers() {
  //   return this.Users.filter((user: any) =>
  //     this.doesUserMatchSearch(user, this.searchTerm)
  //   );
  // }

  get filteredUsers() {
    // Assuming you want to paginate the filtered results
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.Users.slice(startIndex, endIndex).filter((order: any) =>
      this.doesUserMatchSearch(order, this.searchTerm)
    );
  }

  private doesUserMatchSearch(user: any, searchTerm: string): boolean {
    for (const key in user) {
      if (Object.prototype.hasOwnProperty.call(user, key)) {
        const value = user[key];

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
    const totalPages = Math.ceil(this.totalItems / this.itemsPerPage);

    this.pages = Array.from({ length: totalPages }, (_, index) => index + 1);
  }
}
