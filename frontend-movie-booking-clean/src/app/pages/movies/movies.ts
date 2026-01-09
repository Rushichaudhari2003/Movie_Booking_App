import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-movies',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './movies.html'
})
export class MoviesComponent implements OnInit {

  movies: any[] = [];

  constructor(private api: ApiService, private router: Router) {}

  ngOnInit() {
    this.api.getMovies().subscribe((data: any) => {
      this.movies = data;
    });
  }

  book(movie: any) {
    localStorage.setItem('movie', JSON.stringify(movie));
    this.router.navigate(['/book']);
  }
}
