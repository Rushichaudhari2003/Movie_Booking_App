import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private username: string = '';

  constructor() {}

  setUsername(name: string) {
    this.username = name;

    if (typeof window !== 'undefined') {
      localStorage.setItem('username', name);
    }
  }

  getUsername(): string {
    if (this.username) return this.username;

    if (typeof window !== 'undefined') {
      return localStorage.getItem('username') || '';
    }

    return '';
  }

  logout() {
    this.username = '';
    if (typeof window !== 'undefined') {
      localStorage.removeItem('username');
    }
  }
}
