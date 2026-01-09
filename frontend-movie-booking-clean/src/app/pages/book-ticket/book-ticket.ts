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

    // build seats A1 - A50
    for (let i = 1; i <= 50; i++) {
      this.allSeats.push(`A${i}`);
    }
  }

  toggleSeat(seat: string) {
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

    this.api.bookTicket(this.movieName, payload).subscribe({
      next: () => alert('🎉 Ticket booked successfully'),
      error: () => alert('❌ Booking failed')
    });
  }
}
