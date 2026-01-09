import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api';
import { Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';
@Component({
  selector: 'app-my-tickets',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './my-tickets.html',
  styleUrls: ['./my-tickets.css']
})
export class MyTicketsComponent implements OnInit {

  tickets: any[] = [];
constructor(
  private api: ApiService,
  @Inject(PLATFORM_ID) private platformId: Object,
  private cd: ChangeDetectorRef
) {}


  ngOnInit() {

  if (isPlatformBrowser(this.platformId)) {

    const username = localStorage.getItem('username');
    console.log('Loading tickets for:', username);

    if (username) {
      this.api.getMyTickets(username).subscribe({
  next: (res) => {
    this.tickets = res;
    this.cd.detectChanges();   // 🔥 forces immediate UI update
  },
  error: (err) => {
    console.error('Ticket load failed:', err);
  }
});
    }
  }

}

}
