import { Routes } from '@angular/router';
import { MainLayoutComponent } from './layout/main-layout/main-layout';
import { HomeComponent } from './pages/home/home';
import { LoginComponent } from './pages/login/login';
import { RegisterComponent } from './pages/register/register';
import { ResetPasswordComponent } from './pages/reset-password/reset-password';
import { MyTicketsComponent } from './pages/my-tickets/my-tickets';
import { AdminComponent } from './pages/admin/admin';

import { BookTicketComponent } from './pages/book-ticket/book-ticket';
export const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'home', component: HomeComponent },
      { path: 'my-tickets', component: MyTicketsComponent },
      { path: 'reset-password', component: ResetPasswordComponent },
      { path: 'admin', component: AdminComponent },

      {
        path: 'book/:movieName/:theatreName',
        loadComponent: () =>
          import('./pages/book-ticket/book-ticket').then(m => m.BookTicketComponent)
      }
    ]
  }
];
