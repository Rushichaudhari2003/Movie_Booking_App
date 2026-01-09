import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {

  username = '';
  password = '';
  errorMessage = '';

   constructor(
  private api: ApiService,
  private router: Router,
  private auth: AuthService
) {}

  login() {
    this.api.login(this.username, this.password).subscribe({
       next: () => {
  this.auth.setUsername(this.username);
  this.router.navigate(['/home']);
},
      error: () => {
        this.errorMessage = 'Invalid username or password';
      }
    });
  }
}
