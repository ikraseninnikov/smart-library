import { Component, OnInit, NgZone, ChangeDetectorRef  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { LoanService } from '../../../core/services/loan.service';
import { Loan } from '../loan';

@Component({
  selector: 'app-loan-list',
  templateUrl: './loan-list.html',
  styleUrls: ['./loan-list.css'],
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterModule, MatButtonModule, MatIconModule, MatToolbarModule],
  providers: [LoanService]
})
export class LoanListComponent implements OnInit {

  loans: Loan[] = [];
  errorMessage: string = '';

  constructor(private loanService: LoanService, private cd: ChangeDetectorRef) { }

  ngOnInit(): void {
    console.log('LoanListComponent ngOnInit');
    this.loadLoans();
  }

  loadLoans(): void {
    this.loanService.getLoans().subscribe({
      next: (data) => {
        this.loans.length = 0;
        this.loans.push(...data);
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error('Error when loading loans', err);
        this.errorMessage = 'Error when loading loans';
      }
    });
  }

  deleteLoan(id: number): void {
    this.loanService.deleteLoan(id).subscribe({
      next: () => {
        this.loans = this.loans.filter(loan => loan.id !== id);
      },
      error: (err: any) => console.error('Error on loan delete', err)
    });
  }
}
