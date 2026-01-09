import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class TicketService {

  private baseUrl = 'http://localhost:8080/api/v1.0/moviebooking';

  constructor(private http: HttpClient) {}

  bookTicket(movieName: string, ticket: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/${movieName}/add`, ticket);
  }

  getMyTickets(username: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/tickets/${username}`);
  }
}
