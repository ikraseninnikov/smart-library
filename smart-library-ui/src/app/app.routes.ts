import { Routes } from '@angular/router';
import { HomeComponent } from './home/home';
import { BookListComponent } from './features/books/book-list/book-list';
import { UserListComponent } from './features/users/user-list/user-list';
import { LoanListComponent } from './features/loans/loan-list/loan-list';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'books', component: BookListComponent },
  { path: 'users', component: UserListComponent },
  { path: 'loans', component: LoanListComponent },
];
