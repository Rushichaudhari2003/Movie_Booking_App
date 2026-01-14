package com.moviebookingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviebookingapp.dto.TicketRequest;
import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.MovieId;
import com.moviebookingapp.model.Ticket;
import com.moviebookingapp.model.User;
import com.moviebookingapp.repository.TicketRepository;
import com.moviebookingapp.repository.UserRepository;
import com.moviebookingapp.repository.MovieRepository;


@Service
public class TicketService {

    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;
    private final MovieRepository movieRepo;


    public TicketService(TicketRepository ticketRepo,
                     UserRepository userRepo,
                     MovieRepository movieRepo) {
    this.ticketRepo = ticketRepo;
    this.userRepo = userRepo;
    this.movieRepo = movieRepo;
}


/*     public Ticket bookTicket(String movieName, TicketRequest req) {

        User user = userRepo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Ticket ticket = new Ticket();
        ticket.setMovieName(req.getMovieName());
        ticket.setTheatreName(req.getTheatreName());
        ticket.setSeats(req.getSeatNumber());
        ticket.setQuantity(req.getNumberOfTickets());
        ticket.setStatus("Confirmed");
        ticket.setUser(user);

        return ticketRepo.save(ticket);
    } */
  public Ticket bookTicket(String movieName, TicketRequest req) {

    User user = userRepo.findByUsername(req.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    Movie movie = movieRepo.findById(new MovieId(req.getMovieName(), req.getTheatreName()))
            .orElseThrow(() -> new RuntimeException("Movie not found"));

    if (movie.getAvailableTickets() < req.getNumberOfTickets()) {
        throw new RuntimeException("Not enough tickets available");
    }

    // Reduce availability
    movie.setAvailableTickets(movie.getAvailableTickets() - req.getNumberOfTickets());

    // Auto status update
    if (movie.getAvailableTickets() == 0) {
        movie.setStatus("SOLD OUT");
    } else if (movie.getAvailableTickets() <= movie.getTotalTickets() / 2) {
        movie.setStatus("BOOK ASAP");
    } else {
        movie.setStatus("Available");
    }

    movieRepo.save(movie);

    Ticket ticket = new Ticket();
    ticket.setMovieName(req.getMovieName());
    ticket.setTheatreName(req.getTheatreName());
    ticket.setSeats(req.getSeatNumber());
    ticket.setQuantity(req.getNumberOfTickets());
    ticket.setStatus("Confirmed");
    ticket.setUser(user);

    return ticketRepo.save(ticket);
}

    public List<Ticket> getTicketsByUsername(String username) {
        return ticketRepo.findByUserUsername(username);
    }
}
