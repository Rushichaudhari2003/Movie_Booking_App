import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {

  user = {
    firstName: '',
    lastName: '',
    email: '',
    username: '',
    password: '',
    confirmPassword: '',
    contactNumber: ''
  };

  successMessage = '';
  errorMessage = '';

  constructor(private api: ApiService, private router: Router) {}

  passwordRules() {
    const p = this.user.password || '';
    return {
      length: p.length >= 8,
      upper: /[A-Z]/.test(p),
      lower: /[a-z]/.test(p),
      number: /\d/.test(p),
      special: /[!@#$%^&*]/.test(p)
    };
  }

  passwordsMatch() {
    return this.user.password === this.user.confirmPassword;
  }

  isPasswordValid() {
    const r = this.passwordRules();
    return r.length && r.upper && r.lower && r.number && r.special;
  }

  register() {

    if (!this.isPasswordValid() || !this.passwordsMatch()) {
      this.errorMessage = 'Password does not meet all requirements';
      this.successMessage = '';
      return;
    }

    this.api.register(this.user).subscribe({
      next: () => {
        this.successMessage = '✔ Registration successful! Redirecting to login...';
        this.errorMessage = '';

        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
  /*     error: (err) => {
  console.log('Registration error:', err);
  this.errorMessage = err?.error || 'Registration failed';
  this.successMessage = '';
} */
error: (err) => {
  console.log('Registration error:', err);

  if (typeof err?.error === 'string') {
    this.errorMessage = '❌ ' + err.error;
  } else {
    this.errorMessage = '❌ Registration failed';
  }

  this.successMessage = '';
}


    });
  }
}
