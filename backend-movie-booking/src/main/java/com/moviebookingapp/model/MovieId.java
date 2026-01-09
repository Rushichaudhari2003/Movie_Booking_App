package com.moviebookingapp.model;

import java.io.Serializable;

public class MovieId implements Serializable {

    private String movieName;
    private String theatreName;

    public MovieId() {}

    public MovieId(String movieName, String theatreName) {
        this.movieName = movieName;
        this.theatreName = theatreName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieId)) return false;
        MovieId that = (MovieId) o;
        return movieName.equals(that.movieName) &&
               theatreName.equals(that.theatreName);
    }

    @Override
    public int hashCode() {
        return movieName.hashCode() + theatreName.hashCode();
    }
}
