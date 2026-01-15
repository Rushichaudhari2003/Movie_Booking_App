import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ApiService {

  private baseUrl = 'http://localhost:8080/api/v1.0/moviebooking';

  constructor(private http: HttpClient) {}

  // ---------- AUTH ----------

  register(user: any) {
    return this.http.post('http://localhost:8080/api/auth/register', user);
  }

  login(username: string, password: string) {
    return this.http.post<any>('http://localhost:8080/api/auth/login', {
      username,
      password
    });
  }

  // ---------- MOVIES ----------

  getMovies() {
    return this.http.get<any[]>(this.baseUrl + '/all');
  }

  searchMovie(name: string) {
    return this.http.get<any[]>(this.baseUrl + '/movies/search/' + name);
  }

  // ---------- BOOKING ----------

  bookTicket(ticket: any) {
    const token = localStorage.getItem('token');

    return this.http.post(
      `${this.baseUrl}/book`,
      ticket,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
  }

  getMyTickets(username: string) {
    const token = localStorage.getItem('token');

    return this.http.get<any[]>(
      `${this.baseUrl}/tickets/${username}`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
  }

  resetPassword(username: string, newPassword: string) {
    return this.http.post(
      `${this.baseUrl}/reset-password?username=${username}&newPassword=${newPassword}`,
      {},
      { responseType: 'text' }
    );
  }

  // ---------- ADMIN ----------

getAllMoviesForAdmin() {
  const token = localStorage.getItem('token');

  return this.http.get<any[]>('http://localhost:8080/admin/movies', {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
}


  addMovie(movie: any) {
    const token = localStorage.getItem('token');

    return this.http.post('http://localhost:8080/admin/add', movie, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  deleteMovie(movieName: string, theatreName: string) {
    const token = localStorage.getItem('token');

    return this.http.delete(
      `http://localhost:8080/admin/delete?movieName=${movieName}&theatreName=${theatreName}`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
  }

  markSoldOut(movieName: string, theatreName: string) {
    const token = localStorage.getItem('token');

    return this.http.put(
      `http://localhost:8080/admin/mark-sold-out?movieName=${movieName}&theatreName=${theatreName}`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
  }

  markBookAsap(movieName: string, theatreName: string) {
    const token = localStorage.getItem('token');

    return this.http.put(
      `http://localhost:8080/admin/mark-book-asap?movieName=${movieName}&theatreName=${theatreName}`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
  }

  refreshStatus(movieName: string, theatreName: string) {
    const token = localStorage.getItem('token');

    return this.http.put(
      `http://localhost:8080/admin/refresh-status?movieName=${movieName}&theatreName=${theatreName}`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
  }
}
