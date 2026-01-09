 
package com.moviebookingapp.model;

import jakarta.persistence.*;

@Entity
@IdClass(MovieId.class)
public class Movie {

    @Id
    private String movieName;

    @Id
    private String theatreName;

    private int totalTickets;
    private int availableTickets;
    private String status;

    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }

    public String getTheatreName() { return theatreName; }
    public void setTheatreName(String theatreName) { this.theatreName = theatreName; }

    public int getTotalTickets() { return totalTickets; }
    public void setTotalTickets(int totalTickets) { this.totalTickets = totalTickets; }

    public int getAvailableTickets() { return availableTickets; }
    public void setAvailableTickets(int availableTickets) { this.availableTickets = availableTickets; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
