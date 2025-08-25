import { Injectable } from '@angular/core';

export interface Book {
  id: number;
  title: string;
  author: string;
  isAvailable: boolean;
  available?: boolean;
}
