package com.moviebookingapp.controller;

import org.springframework.web.bind.annotation.*;
import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.MovieId;
import com.moviebookingapp.service.MovieService;
import java.util.List;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    private final MovieService service;

    public AdminController(MovieService service){
        this.service = service;
    }

    @PostMapping("/add")
    public Movie add(@RequestBody Movie movie){
        return service.save(movie);
    }
    @GetMapping("/movies")
    public List<Movie> allMovies(){
        return service.getAll();
    }
    @DeleteMapping("/delete")
    public void deleteMovie(@RequestParam String movieName,
                            @RequestParam String theatreName) {
        service.delete(movieName, theatreName);
    }
@PutMapping("/mark-sold-out")
public Movie markSoldOut(@RequestParam String movieName,
                         @RequestParam String theatreName) {
    return service.markSoldOut(movieName, theatreName);
}

@PutMapping("/mark-book-asap")
public Movie markBookAsap(@RequestParam String movieName,
                          @RequestParam String theatreName) {
    return service.markBookAsap(movieName, theatreName);
}

@PutMapping("/refresh-status")
public Movie refreshStatus(@RequestParam String movieName,
                           @RequestParam String theatreName) {
    return service.refreshAvailability(movieName, theatreName);
}


}
