import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LoanListComponent } from './loan-list/loan-list'; // standalone

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    LoanListComponent
  ]
})
export class LoansModule { }
