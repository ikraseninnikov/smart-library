import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { BookListComponent } from './book-list/book-list';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    BookListComponent
  ]
})
export class BooksModule { }
