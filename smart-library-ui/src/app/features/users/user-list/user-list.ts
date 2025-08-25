import { Component, OnInit, NgZone, ChangeDetectorRef  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { UserService } from '../../../core/services/user.service';
import { User } from '../user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.html',
  styleUrls: ['./user-list.css'],
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterModule, MatButtonModule, MatIconModule, MatToolbarModule],
  providers: [UserService]
})
export class UserListComponent implements OnInit {

  users: User[] = [];
  errorMessage: string = '';

  constructor(private userService: UserService, private cd: ChangeDetectorRef) { }

  ngOnInit(): void {
    console.log('UserListComponent ngOnInit');
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe({
      next: (data) => {
        this.users.length = 0;
        this.users.push(...data);
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error('Error when loading users', err);
        this.errorMessage = 'Error when loading users';
      }
    });
  }

  deleteUser(id: number): void {
    this.userService.deleteUser(id).subscribe({
      next: () => {
        this.users = this.users.filter(user => user.id !== id);
      },
      error: (err: any) => console.error('Error on user delete', err)
    });
  }
}
