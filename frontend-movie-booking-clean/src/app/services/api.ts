import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ApiService {

  private baseUrl = 'http://localhost:8080/api/v1.0/moviebooking';

  constructor(private http: HttpClient) {}

  register(user: any) {
    return this.http.post(this.baseUrl + '/register', user, { responseType: 'text' });
  }

  login(username: string, password: string) {
    return this.http.get<any>(this.baseUrl + '/login?username=' + username + '&password=' + password);
  }

  getMovies() {
    return this.http.get<any[]>(this.baseUrl + '/all');
  }

  searchMovie(name: string) {
    return this.http.get<any[]>(this.baseUrl + '/movies/search/' + name);
  }

 bookTicket(movieName: string, ticket: any) {
  return this.http.post(
    `http://localhost:8080/api/v1.0/moviebooking/${movieName}/add`,
    ticket
  );
}

 getMyTickets(username: string) {
  return this.http.get<any[]>(
    `http://localhost:8080/api/v1.0/moviebooking/tickets/${username}`
  );
}



}
