package com.moviebookingapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.MovieId;
import com.moviebookingapp.repository.MovieRepository;

@Service
public class MovieService {
    private final MovieRepository repo;

    public MovieService(MovieRepository repo){
        this.repo = repo;
    }

    public List<Movie> getAll(){
        return repo.findAll();
    }

    public List<Movie> search(String name){
        return repo.findByMovieNameContainingIgnoreCase(name);
    }

    public Movie save(Movie movie){
        return repo.save(movie);
    }

    public void delete(MovieId id){
        repo.deleteById(id);
    }
}
