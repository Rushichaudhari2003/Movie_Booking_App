/* import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api';

@Component({
    
  standalone: true,
  selector: 'app-admin',
  imports: [CommonModule, FormsModule],
  template: `
    <h1 style="text-align:center">🎬 Admin Dashboard</h1>
    <p style="text-align:center">Manage movies, ticket availability & status</p>
  `
})
export class AdminComponent {}
 */
/* import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.html',
  styleUrls: ['./admin.css']
})
export class AdminComponent {

  // 👇 FIX: Added missing variable
  successMessage = '';

  movies: any[] = [];

  newMovie = {
    movieName: '',
    theatreName: '',
    totalTickets: 0,
    availableTickets: 0,
    status: 'Available'
  };

  constructor(private api: ApiService) {
    this.loadMovies();
  }

  loadMovies() {
    this.api.getMovies().subscribe(data => {
      this.movies = data;
    });
  }

  addMovie() {
    this.newMovie.availableTickets = this.newMovie.totalTickets;
    this.newMovie.status = 'Available';

    this.api.addMovie(this.newMovie).subscribe(() => {
      this.successMessage = '🎉 Movie added successfully';

      setTimeout(() => this.successMessage = '', 3000);

      this.resetForm();
      this.loadMovies();
    });
  }

  deleteMovie(m: any) {
    this.api.deleteMovie(m.movieName, m.theatreName).subscribe(() => {
      this.loadMovies();
    });
  }

  markSoldOut(m: any) {
    m.availableTickets = 0;
    m.status = 'Sold Out';

    this.api.addMovie(m).subscribe(() => {
      this.loadMovies();
    });
  }

  markBookASAP(m: any) {
    m.status = 'Book ASAP';

    this.api.addMovie(m).subscribe(() => {
      this.loadMovies();
    });
  }

  refreshAvailability(m: any) {
    m.availableTickets = m.totalTickets;
    m.status = 'Available';

    this.api.addMovie(m).subscribe(() => {
      this.loadMovies();
    });
  }

  resetForm() {
    this.newMovie = {
      movieName: '',
      theatreName: '',
      totalTickets: 0,
      availableTickets: 0,
      status: 'Available'
    };
  }
}
 */
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.html',
  styleUrls: ['./admin.css']
})
export class AdminComponent implements OnInit {

  successMessage = '';

  movies: any[] = [];

  newMovie = {
    movieName: '',
    theatreName: '',
    totalTickets: 0,
    availableTickets: 0,
    status: 'Available'
  };
  

  /* constructor(private api: ApiService) {} */
  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}


  // 🔥 This fixes the "blank until click" problem
  ngOnInit(): void {
  this.loadMovies();
}

  loadMovies() {
    this.api.getMovies().subscribe(data => {
      this.movies = data;
      this.cdr.detectChanges();
    });
  }

  addMovie() {
    this.newMovie.availableTickets = this.newMovie.totalTickets;
    this.newMovie.status = 'Available';

    this.api.addMovie(this.newMovie).subscribe(() => {
      this.successMessage = '🎉 Movie added successfully';

      setTimeout(() => this.successMessage = '', 3000);

      this.resetForm();
      this.loadMovies();
    });
  }

/*   deleteMovie(m: any) {
    this.api.deleteMovie(m.movieName, m.theatreName).subscribe(() => {
      this.loadMovies();
    });
  } */
/*  deleteMovie(m: any) {
  if (!confirm(`Delete ${m.movieName}?`)) return;

  this.api.deleteMovie(m.movieName, m.theatreName).subscribe(() => {
    
    // 🔥 remove the card immediately from UI
    this.movies = this.movies.filter(
      x => !(x.movieName === m.movieName && x.theatreName === m.theatreName)
    );

    // optional safety reload from backend
    this.loadMovies();
  });
} */

  
/* confirmDelete(m: any) {
  if (confirm(`Delete ${m.movieName}?`)) {
    this.deleteMovie(m);
  }
} */
confirmDelete(m: any) {

  if (!confirm(`Delete ${m.movieName}?`)) return;

  // Remove immediately from UI
  this.movies = this.movies.filter(
    x => !(x.movieName === m.movieName && x.theatreName === m.theatreName)
  );

  // Then tell backend
  this.api.deleteMovie(m.movieName, m.theatreName).subscribe({
    next: () => {
      console.log('Deleted successfully');
    },
    error: err => {
      console.warn('Backend already deleted this item, ignoring error:', err);
    }
  });
}



 markSoldOut(m: any) {
  this.api.markSoldOut(m.movieName, m.theatreName).subscribe(() => {
    this.loadMovies();
  });
}

 markBookASAP(m: any) {
  this.api.markBookAsap(m.movieName, m.theatreName).subscribe(() => {
    this.loadMovies();
  });
}

refreshAvailability(m: any) {
  this.api.refreshStatus(m.movieName, m.theatreName).subscribe(() => {
    this.loadMovies();
  });
}


  resetForm() {
    this.newMovie = {
      movieName: '',
      theatreName: '',
      totalTickets: 0,
      availableTickets: 0,
      status: 'Available'
    };
  }
  trackMovie(index: number, m: any) {
    return m.movieName + '|' + m.theatreName;
  }
}
