import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api';
import { AuthService } from '../../services/auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule,RouterModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
   
})
export class LoginComponent {

  username = '';
  password = '';
  errorMessage = '';

  showPassword = false;

togglePassword() {
  this.showPassword = !this.showPassword;
}

   constructor(
  private api: ApiService,
  private router: Router,
  private auth: AuthService
) {}

 /* 
  login() {
  this.api.login(this.username, this.password).subscribe({
    next: (user) => {
      this.auth.setUsername(user.username);
      this.auth.setRole(user.role);

      if (user.role === 'ADMIN') {
        this.router.navigate(['/admin']);
      } else {
        this.router.navigate(['/home']);
      }
    },
    error: (err) => {
      this.errorMessage = err.error || 'Invalid username or password';
    }
    
  });
}
 */
login() {
  this.api.login(this.username, this.password).subscribe({
    next: (res) => {
      localStorage.setItem('token', res.token);   // 👈 very important

      this.auth.setUsername(res.username);
      this.auth.setRole(res.role);

      if (res.role === 'ADMIN') {
        this.router.navigate(['/admin']);
      } else {
        this.router.navigate(['/home']);
      }
    },
    error: (err) => {
      this.errorMessage = err.error || 'Invalid username or password';
    }
  });
}

}
