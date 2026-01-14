import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ApiService {

  private baseUrl = 'http://localhost:8080/api/v1.0/moviebooking';

  constructor(private http: HttpClient) {}

  // register(user: any) {
  //   return this.http.post(this.baseUrl + '/register', user, { responseType: 'text' });
  // }/
  register(user: any) {
  return this.http.post(
    'http://localhost:8080/api/auth/register',
    user
  );
}

 /*  login(username: string, password: string) {
    return this.http.get<any>(this.baseUrl + '/login?username=' + username + '&password=' + password);
  }
 */
login(username: string, password: string) {
  return this.http.post<any>(
    'http://localhost:8080/api/auth/login?username=' + username + '&password=' + password,
    {}
  );
}
  getMovies() {
    return this.http.get<any[]>(this.baseUrl + '/all');
  }

  searchMovie(name: string) {
    return this.http.get<any[]>(this.baseUrl + '/movies/search/' + name);
  }

/*  bookTicket(movieName: string, ticket: any) {
  return this.http.post(
    `http://localhost:8080/api/v1.0/moviebooking/${movieName}/book`, ticket
  );
} */
bookTicket(ticket: any) {
  return this.http.post(
    `http://localhost:8080/api/v1.0/moviebooking/book`,
    ticket
  );
}


 getMyTickets(username: string) {
  return this.http.get<any[]>(
    `http://localhost:8080/api/v1.0/moviebooking/tickets/${username}`
  );
}

 resetPassword(username: string, newPassword: string) {
  return this.http.post(
    this.baseUrl + '/reset-password?username=' + username + '&newPassword=' + newPassword,
    {},
    { responseType: 'text' }
  );
}    
  

 
// ----------------- ADMIN APIs -----------------

getAllMoviesForAdmin() {
  return this.http.get<any[]>('http://localhost:8080/admin/all');
}

addMovie(movie: any) {
  return this.http.post('http://localhost:8080/admin/add', movie);
}
deleteMovie(movieName: string, theatreName: string) {
  return this.http.delete(
    `http://localhost:8080/admin/delete?movieName=${movieName}&theatreName=${theatreName}`
  );
}

markSoldOut(movieName: string, theatreName: string) {
  return this.http.put(
    `http://localhost:8080/admin/mark-sold-out?movieName=${movieName}&theatreName=${theatreName}`,
    {}
  );
}

markBookAsap(movieName: string, theatreName: string) {
  return this.http.put(
    `http://localhost:8080/admin/mark-book-asap?movieName=${movieName}&theatreName=${theatreName}`,
    {}
  );
}
refreshStatus(movieName: string, theatreName: string) {
  return this.http.put(
    `http://localhost:8080/admin/refresh-status?movieName=${movieName}&theatreName=${theatreName}`,
    {}
  );
}


}
