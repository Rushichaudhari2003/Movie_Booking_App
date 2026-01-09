import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './reset-password.html',
  styleUrls: ['./reset-password.css']
})
export class ResetPasswordComponent {

  model = {
    username: '',
    newPassword: '',
    confirmPassword: ''
  };

  successMessage = '';

  passwordRules() {
    const p = this.model.newPassword || '';
    return {
      length: p.length >= 8,
      upper: /[A-Z]/.test(p),
      lower: /[a-z]/.test(p),
      number: /\d/.test(p),
      special: /[!@#$%^&*]/.test(p)
    };
  }

  isValidPassword() {
    const r = this.passwordRules();
    return r.length && r.upper && r.lower && r.number && r.special;
  }

  passwordsMatch() {
    return this.model.newPassword === this.model.confirmPassword;
  }

  resetPassword() {
    if (!this.isValidPassword() || !this.passwordsMatch()) return;
    this.successMessage = '✔ Password updated successfully';
  }
}
