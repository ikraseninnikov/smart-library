import { Component, OnInit, NgZone, ChangeDetectorRef  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { BookService } from '../../../core/services/book.service';
import { Book } from '../book';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.html',
  styleUrls: ['./book-list.css'],
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterModule, MatButtonModule, MatIconModule, MatToolbarModule],
  providers: [BookService]
})
export class BookListComponent implements OnInit {

  books: Book[] = [];
  errorMessage: string = '';

  constructor(private bookService: BookService, private cd: ChangeDetectorRef) { }

  ngOnInit(): void {
    console.log('BookListComponent ngOnInit');
    this.loadBooks();
  }

  loadBooks(): void {
    this.bookService.getBooks().subscribe({
      next: (data) => {
        this.books.length = 0;
        this.books.push(...data);
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error('Error when loading books', err);
        this.errorMessage = 'Error when loading books';
      }
    });
  }

  deleteBook(id: number): void {
    this.bookService.deleteBook(id).subscribe({
      next: () => {
        this.books = this.books.filter(book => book.id !== id);
      },
      error: (err: any) => console.error('Error on book delete', err)
    });
  }
}
