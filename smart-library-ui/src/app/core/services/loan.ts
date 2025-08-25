import { Injectable } from '@angular/core';

export class Loan {
  id?: number;
  userId?: number;
  bookId?: number;
  userName?: string;
  userEmail?: string;
  bookTitle?: string;
  bookAuthor?: string;
  loanDate?: string;
  returnDate?: string;
}
