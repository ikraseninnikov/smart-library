import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Routes } from '@angular/router';
import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { App } from './app';
import { HomeComponent } from './home/home';
import { BookListComponent } from './features/books/book-list/book-list';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'books', component: BookListComponent },
];

bootstrapApplication(App, {
  providers: [
    importProvidersFrom(HttpClientModule), // <-- добавлено
    provideRouter(routes)
  ]
}).catch(err => console.error(err));
