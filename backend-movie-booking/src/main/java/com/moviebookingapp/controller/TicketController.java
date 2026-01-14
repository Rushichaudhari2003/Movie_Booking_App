package com.moviebookingapp.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.moviebookingapp.model.Ticket;
import com.moviebookingapp.service.TicketService;
import com.moviebookingapp.dto.TicketRequest;


@RestController
@RequestMapping("/api/v1.0/moviebooking")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

  /*   @PostMapping("/{movieName}/book")
    public Ticket bookTicket(@PathVariable String movieName,
                             @RequestBody TicketRequest request) {
        return service.bookTicket(movieName, request);
    }

    @GetMapping("/internal/tickets/{username}")
    public List<Ticket> getTickets(@PathVariable String username) {
        return service.getTicketsByUsername(username);
    } */
   
   @PostMapping("/book")
    public Ticket bookTicket(@RequestBody TicketRequest request) {
        return service.bookTicket(request.getMovieName(), request);
    }

   /*  @GetMapping("/tickets/{username}")
    public List<Ticket> getTickets(@PathVariable String username) {
        return service.getTicketsByUsername(username);
    } */
   
}

