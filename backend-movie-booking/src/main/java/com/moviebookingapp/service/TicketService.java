package com.moviebookingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviebookingapp.dto.TicketRequest;
import com.moviebookingapp.model.Ticket;
import com.moviebookingapp.model.User;
import com.moviebookingapp.repository.TicketRepository;
import com.moviebookingapp.repository.UserRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;

    public TicketService(TicketRepository ticketRepo, UserRepository userRepo) {
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
    }

    public Ticket bookTicket(String movieName, TicketRequest req) {

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
    }

    public List<Ticket> getTicketsByUsername(String username) {
        return ticketRepo.findByUserUsername(username);
    }
}
