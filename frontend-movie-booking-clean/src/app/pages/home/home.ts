/* import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-home',
  standalone: true,
    imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class HomeComponent {

  constructor(private api: ApiService) {}

  searchText = '';

  movies = [
    { name: 'Avatar', theatre: 'PVR', available: 100, status: 'Available' },
    { name: 'Leo', theatre: 'Cinepolis', available: 120, status: 'Available' },
    { name: 'Inception', theatre: 'Inox', available: 78, status: 'Available' },
    { name: 'Dune 2', theatre: 'PVR', available: 146, status: 'Available' },
    { name: 'Barbie', theatre: 'PVR', available: 0, status: 'Sold Out' },
    { name: 'Don', theatre: 'Inox', available: 4, status: 'Book ASAP' }
  ];

  filteredMovies = [...this.movies];

  search() {
    this.filteredMovies = this.movies.filter(m =>
      m.name.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

   book(m: any) {

  const username = localStorage.getItem('username');

  if (!username) {
    alert('Please login first');
    return;
  }

  const payload = {
    movieName: m.name,
    theatreName: m.theatre,
    seatNumber: 'A1',          // backend field name
    numberOfTickets: 1,       // backend field name
    username: username
  };

 

  this.api.bookTicket(payload).subscribe({
    next: () => {
      alert('🎉 Ticket booked successfully');
    },
    error: (err) => {
      console.error('Booking error:', err);
      alert('❌ Booking failed');
    }
  });
}
}
 */
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api';
import { RouterModule } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class HomeComponent implements OnInit {

 constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}


  searchText = '';

  movies: any[] = [];
  filteredMovies: any[] = [];

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies() {
    this.api.getMovies().subscribe(data => {
      this.movies = data;
      this.filteredMovies = data;
        this.cdr.detectChanges();
      
    });
  }

  search() {
    this.filteredMovies = this.movies.filter(m =>
      m.movieName.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  book(m: any) {
    const username = localStorage.getItem('username');

    if (!username) {
      alert('Please login first');
      return;
    }

    const payload = {
      movieName: m.movieName,
      theatreName: m.theatreName,
      seatNumber: 'A1',
      numberOfTickets: 1,
      username: username
    };

    this.api.bookTicket(payload).subscribe({
      next: () => {
        alert('🎉 Ticket booked successfully');
        this.loadMovies();   // refresh availability
      },
      error: (err) => {
        console.error('Booking error:', err);
        alert('❌ Booking failed');
      }
    });
  }
}
