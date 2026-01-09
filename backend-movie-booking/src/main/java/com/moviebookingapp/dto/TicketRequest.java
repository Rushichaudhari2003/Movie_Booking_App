package com.moviebookingapp.dto;

public class TicketRequest {

    private String username;
    private String movieName;
    private String theatreName;
    private String seatNumber;
    private int numberOfTickets;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }

    public String getTheatreName() { return theatreName; }
    public void setTheatreName(String theatreName) { this.theatreName = theatreName; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public int getNumberOfTickets() { return numberOfTickets; }
    public void setNumberOfTickets(int numberOfTickets) { this.numberOfTickets = numberOfTickets; }
}
