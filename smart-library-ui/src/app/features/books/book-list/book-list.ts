import { Component, OnInit, NgZone, ChangeDetectorRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { BookService } from '../../../core/services/book.service';
import { Book } from '../book';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.html',
  styleUrls: ['./book-list.css'],
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatCardModule,
    MatGridListModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule
    ],
  providers: [BookService]
})
export class BookListComponent implements OnInit {

  books: Book[] = [];
  dataSource = new MatTableDataSource<Book>();
  displayedColumns: string[] = ['title', 'author', 'isbn', 'status', 'actions'];
  errorMessage: string = '';

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private bookService: BookService,
    private router: Router,
    private cd: ChangeDetectorRef) { }

  ngOnInit(): void {
    console.log('BookListComponent ngOnInit');
    this.loadBooks();
  }

  ngAfterViewInit(): void {
    if (this.dataSource) {
      this.dataSource.paginator = this.paginator;
    }
  }

  loadBooks(): void {
    this.bookService.getBooks().subscribe({
      next: (data) => {
        this.books = data;
        this.dataSource = new MatTableDataSource(this.books);
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error('Error when loading books', err);
        this.errorMessage = 'Error when loading books';
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  editBook(book: Book): void {
    this.router.navigate(['/books', 'edit', book.id]).catch((err: any) => {
      console.error('Error routing edit book', err);
    });
  }

  deleteBook(id: number): void {
    console.log('Book delete with id=' + id);
    this.bookService.deleteBook(id).subscribe({
      next: () => {
        this.books = this.books.filter(book => book.id !== id);
        this.dataSource.data = this.books;
        this.cd.detectChanges();
      },
      error: (err: any) => console.error('Error on book delete', err)
    });
  }
}
