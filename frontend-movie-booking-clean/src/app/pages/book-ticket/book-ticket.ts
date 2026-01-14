import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-book-ticket',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './book-ticket.html',
  styleUrls: ['./book-ticket.css']
})
export class BookTicketComponent implements OnInit {
 availableTickets = 0;
limitMessage = '';

  movieName!: string;
  theatreName!: string;

  allSeats: string[] = [];
  selectedSeats: string[] = [];

  constructor(
    private route: ActivatedRoute,
    private api: ApiService
  ) {}

  ngOnInit(): void {
    this.movieName = this.route.snapshot.paramMap.get('movieName')!;
    this.theatreName = this.route.snapshot.paramMap.get('theatreName')!;

    // 🧱 Restore your original working seat grid (A1–A50)
    this.allSeats = [];
    for (let i = 1; i <= 50; i++) {
      this.allSeats.push(`A${i}`);
    }
    
    this.api.getMovies().subscribe(movies => {
 const movie = movies.find(m =>
  m.movieName === this.movieName &&
  m.theatreName === this.theatreName
);

  if (movie) {
   this.availableTickets = movie.availableTickets;
  }
});

  }

 /*  toggleSeat(seat: string) {
    if (this.selectedSeats.includes(seat)) {
      this.selectedSeats = this.selectedSeats.filter(s => s !== seat);
    } else {
      this.selectedSeats.push(seat);
    }
  }
 */
toggleSeat(seat: string) {

  if (!this.selectedSeats.includes(seat) &&
      this.selectedSeats.length >= this.availableTickets) {

    this.limitMessage = `❌ You can only book ${this.availableTickets} tickets for this show`;
    return;
  }

  this.limitMessage = '';

  if (this.selectedSeats.includes(seat)) {
    this.selectedSeats = this.selectedSeats.filter(s => s !== seat);
  } else {
    this.selectedSeats.push(seat);
  }
}

  confirmBooking() {
    const username = localStorage.getItem('username');

    if (!username || this.selectedSeats.length === 0) {
      alert('Please select seats');
      return;
    }

    const payload = {
      username,
      movieName: this.movieName,
      theatreName: this.theatreName,
      seatNumber: this.selectedSeats.join(','),
      numberOfTickets: this.selectedSeats.length
    };

    this.api.bookTicket(payload).subscribe({

      next: () => alert('🎉 Ticket booked successfully'),
      error: err => {
        console.error(err);
        alert('❌ Booking failed');
      }
    });
  }
}
