import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Loan } from '../../features/loans/loan';

@Injectable({
  providedIn: 'root'
})
export class LoanService {

  private apiUrl = 'http://localhost:8080/api/loans';

  constructor(private http: HttpClient) { }

  getLoans(): Observable<Loan[]> {
    console.log('HTTP GET /loans called');
    return this.http.get<Loan[]>(this.apiUrl);
  }

  getLoan(id: number): Observable<Loan> {
    return this.http.get<Loan>(`${this.apiUrl}/${id}`);
  }

  createLoan(loan: Loan): Observable<Loan> {
    return this.http.post<Loan>(this.apiUrl, loan);
  }

  updateLoan(loan: Loan): Observable<Loan> {
    return this.http.put<Loan>(`${this.apiUrl}/${loan.id}`, loan);
  }

  deleteLoan(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
