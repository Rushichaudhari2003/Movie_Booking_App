package com.moviebookingapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.MovieId;

public interface MovieRepository extends JpaRepository<Movie, MovieId> {
    List<Movie> findByMovieNameContainingIgnoreCase(String name);
}
