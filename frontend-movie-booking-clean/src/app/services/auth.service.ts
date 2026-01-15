/*  
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private username: string = '';
  private role: string = '';

  constructor() {}

  // ---------- USERNAME ----------

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

  // ---------- ROLE (NEW) ----------

  setRole(role: string) {
    this.role = role;

    if (typeof window !== 'undefined') {
      localStorage.setItem('role', role);
    }
  }

  getRole(): string {
    if (this.role) return this.role;

    if (typeof window !== 'undefined') {
      return localStorage.getItem('role') || '';
    }

    return '';
  }

  isAdmin(): boolean {
    return this.getRole() === 'ADMIN';
  }

  // ---------- LOGIN STATE ----------

  isLoggedIn(): boolean {
    return this.getUsername().length > 0;
  }

  // ---------- LOGOUT ----------

  logout() {
    this.username = '';
    this.role = '';

    if (typeof window !== 'undefined') {
      localStorage.removeItem('username');
      localStorage.removeItem('role');
    }
  }
}

 */

import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private username: string = '';
  private role: string = '';

  // ---------- TOKEN ----------

  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string {
    return localStorage.getItem('token') || '';
  }

  // ---------- USERNAME ----------

  setUsername(name: string) {
    this.username = name;
    localStorage.setItem('username', name);
  }

  getUsername(): string {
    if (this.username) return this.username;
    return localStorage.getItem('username') || '';
  }

  // ---------- ROLE ----------

  setRole(role: string) {
    this.role = role;
    localStorage.setItem('role', role);
  }

  getRole(): string {
    if (this.role) return this.role;
    return localStorage.getItem('role') || '';
  }

  isAdmin(): boolean {
    return this.getRole() === 'ADMIN';
  }

  // ---------- LOGIN STATE ----------

  isLoggedIn(): boolean {
    return this.getToken().length > 0;
  }

  // ---------- LOGOUT ----------

  logout() {
    this.username = '';
    this.role = '';

    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('role');
  }
}
