package com.moviebookingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

     List<Ticket> findByUserUsername(String username);

}
