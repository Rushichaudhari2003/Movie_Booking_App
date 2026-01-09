package com.moviebookingapp.model;

import jakarta.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieName;
    private String theatreName;
    private String seats;
    private int quantity;
    private String status;

   @ManyToOne(optional = false)
@JoinColumn(name = "user_id", nullable = false)
private User user;


    // ---------- Getters & Setters ----------

    public Long getId() { return id; }

    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }

    public String getTheatreName() { return theatreName; }
    public void setTheatreName(String theatreName) { this.theatreName = theatreName; }

    public String getSeats() { return seats; }
    public void setSeats(String seats) { this.seats = seats; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
