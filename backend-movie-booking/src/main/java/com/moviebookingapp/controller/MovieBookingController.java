package com.moviebookingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.moviebookingapp.dto.TicketRequest;
import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.Ticket;
import com.moviebookingapp.model.User;
import com.moviebookingapp.service.MovieService;
import com.moviebookingapp.service.TicketService;
import com.moviebookingapp.service.UserService;

@RestController
@RequestMapping("/api/v1.0/moviebooking")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieBookingController {

    private final UserService userService;
    private final MovieService movieService;
    private final TicketService ticketService;

    public MovieBookingController(UserService userService,
                                  MovieService movieService,
                                  TicketService ticketService) {
        this.userService = userService;
        this.movieService = movieService;
        this.ticketService = ticketService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/login")
    public User login(@RequestParam String username,
                      @RequestParam String password) {
        return userService.login(username, password);
    }

    @GetMapping("/all")
    public List<Movie> allMovies() {
        return movieService.getAll();
    }

    @GetMapping("/movies/search/{name}")
    public List<Movie> search(@PathVariable String name) {
        return movieService.search(name);
    }

    @PostMapping("/{movieName}/add")
    public ResponseEntity<?> book(@PathVariable String movieName,
                                  @RequestBody TicketRequest request) {
        try {
            Ticket ticket = ticketService.bookTicket(movieName, request);
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

   @GetMapping("/tickets/{username}")
public List<Ticket> getMyTickets(@PathVariable String username) {
    return ticketService.getTicketsByUsername(username);
}

}
