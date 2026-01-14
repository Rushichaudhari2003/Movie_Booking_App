/* package com.moviebookingapp.service;

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
        movie.setAvailableTickets(movie.getTotalTickets());
        movie.setStatus("Available");
        return repo.save(movie);
    }
    public void delete(MovieId id) {
    ticketRepo.deleteByMovieNameAndTheatreName(id.getMovieName(), id.getTheatreName());
    repo.deleteById(id);
}

    public Movie markSoldOut(String movieName, String theatreName) {
    Movie movie = repo.findById(new MovieId(movieName, theatreName))
            .orElseThrow(() -> new RuntimeException("Movie not found"));

    movie.setAvailableTickets(0);
    movie.setStatus("SOLD OUT");

    return repo.save(movie);
}

public Movie markBookAsap(String movieName, String theatreName) {
    Movie movie = repo.findById(new MovieId(movieName, theatreName))
            .orElseThrow(() -> new RuntimeException("Movie not found"));

    movie.setStatus("BOOK ASAP");

    return repo.save(movie);
}

public Movie refreshAvailability(String movieName, String theatreName) {

    Movie movie = repo.findById(new MovieId(movieName, theatreName))
            .orElseThrow(() -> new RuntimeException("Movie not found"));

    int available = movie.getAvailableTickets();
    int total = movie.getTotalTickets();

    if (available == 0) {
        movie.setStatus("SOLD OUT");
    } else if (available <= total / 2) {
        movie.setStatus("BOOK ASAP");
    } else {
        movie.setStatus("Available");
    }

    return repo.save(movie);
}


}
 */

package com.moviebookingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.MovieId;
import com.moviebookingapp.repository.MovieRepository;

/* @Service
public class MovieService {

    private final MovieRepository repo;

    public MovieService(MovieRepository repo) {
        this.repo = repo;
    }

    public List<Movie> getAll() {
        return repo.findAll();
    }

    public List<Movie> search(String name) {
        return repo.findByMovieNameContainingIgnoreCase(name);
    }

    public Movie save(Movie movie) {
        return repo.save(movie);
    }

    public void delete(String movieName, String theatreName) {
        repo.deleteByMovieNameAndTheatreName(movieName, theatreName);
    }

    public Movie markSoldOut(String movieName, String theatreName) {
        Movie movie = repo.findById(new MovieId(movieName, theatreName))
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setAvailableTickets(0);
        movie.setStatus("SOLD OUT");

        return repo.save(movie);
    }

    public Movie markBookAsap(String movieName, String theatreName) {
        Movie movie = repo.findById(new MovieId(movieName, theatreName))
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setStatus("BOOK ASAP");

        return repo.save(movie);
    }

    public Movie refreshAvailability(String movieName, String theatreName) {
        Movie movie = repo.findById(new MovieId(movieName, theatreName))
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        int available = movie.getAvailableTickets();
        int total = movie.getTotalTickets();

        if (available == 0) {
            movie.setStatus("SOLD OUT");
        } else if (available <= total / 2) {
            movie.setStatus("BOOK ASAP");
        } else {
            movie.setStatus("Available");
        }

        return repo.save(movie);
    }
}
 */

@Service
public class MovieService {

    private final MovieRepository repo;

    public MovieService(MovieRepository repo) {
        this.repo = repo;
    }

    public List<Movie> getAll() {
        return repo.findAll();
    }

    public List<Movie> search(String name) {
        return repo.findByMovieNameContainingIgnoreCase(name);
    }

    public Movie save(Movie movie) {
        return repo.save(movie);
    }

   /*  public void delete(String movieName, String theatreName) {
        repo.deleteById(new MovieId(movieName, theatreName));
    } */
public void delete(String movieName, String theatreName) {
    MovieId id = new MovieId(movieName, theatreName);

    if (!repo.existsById(id)) {
        throw new RuntimeException("Movie not found");
    }

    repo.deleteById(id);
}

    public Movie markSoldOut(String movieName, String theatreName) {
        Movie movie = repo.findById(new MovieId(movieName, theatreName))
                .orElseThrow();

        movie.setAvailableTickets(0);
        movie.setStatus("SOLD OUT");
        return repo.save(movie);
    }

    public Movie markBookAsap(String movieName, String theatreName) {
        Movie movie = repo.findById(new MovieId(movieName, theatreName))
                .orElseThrow();

        movie.setStatus("BOOK ASAP");
        return repo.save(movie);
    }

   /*  public Movie refreshAvailability(String movieName, String theatreName) {
        Movie movie = repo.findById(new MovieId(movieName, theatreName))
                .orElseThrow();

        int available = movie.getAvailableTickets();
        int total = movie.getTotalTickets();

        if (available == 0) movie.setStatus("SOLD OUT");
        else if (available <= total / 2) movie.setStatus("BOOK ASAP");
        else movie.setStatus("Available");

        return repo.save(movie);
    } */
   public Movie refreshAvailability(String movieName, String theatreName) {

    Movie movie = repo.findById(new MovieId(movieName, theatreName))
            .orElseThrow(() -> new RuntimeException("Movie not found"));

    int available = movie.getAvailableTickets();
    int total = movie.getTotalTickets();

    if (available == 0) {
        movie.setStatus("SOLD OUT");
    } else if (available <= total / 2) {
        movie.setStatus("BOOK ASAP");
    } else {
        movie.setStatus("Available");
    }

    return repo.save(movie);
}

}
