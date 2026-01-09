import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],   // 👈 THIS FIXES ngIf
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.css']
})
export class NavbarComponent implements OnInit {

  username = '';

  constructor(private auth: AuthService) {}

  ngOnInit() {
    this.username = this.auth.getUsername();
  }

  logout() {
    this.auth.logout();
    location.href = '/login';
  }
}
