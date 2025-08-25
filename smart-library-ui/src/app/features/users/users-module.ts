import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UserListComponent } from './user-list/user-list';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    UserListComponent
  ]
})
export class UsersModule { }
