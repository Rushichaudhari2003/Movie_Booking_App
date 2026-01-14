package com.moviebookingapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.Ticket;
import com.moviebookingapp.service.MovieService;
import com.moviebookingapp.service.TicketService;

@RestController
@RequestMapping("/api/v1.0/moviebooking")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieBookingController {

    private final MovieService movieService;
    private final TicketService ticketService;

    public MovieBookingController(MovieService movieService,
                                  TicketService ticketService) {
        this.movieService = movieService;
        this.ticketService = ticketService;
    }

    @GetMapping("/all")
    public List<Movie> allMovies() {
        return movieService.getAll();
    }

    @GetMapping("/movies/search/{name}")
    public List<Movie> search(@PathVariable String name) {
        return movieService.search(name);
    }

    @GetMapping("/tickets/{username}")
    public List<Ticket> getMyTickets(@PathVariable String username) {
        return ticketService.getTicketsByUsername(username);
    }
     @PostMapping("/{movieName}/book")
    public Ticket bookTicket(@PathVariable String movieName,
                             @RequestBody com.moviebookingapp.dto.TicketRequest request) {
        return ticketService.bookTicket(movieName, request);
    }
}
