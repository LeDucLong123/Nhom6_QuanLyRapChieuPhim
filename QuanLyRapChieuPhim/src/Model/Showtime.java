/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ASUS
 */
import java.time.LocalDateTime;

public class Showtime {
    private Movie movie;
    private LocalDateTime time;

    public Showtime(Movie movie, LocalDateTime time) {
        this.movie = movie;
        this.time = time;
    }

    // Getters và setters
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Showtime{" +
                "movie=" + movie.getTitle() +
                ", time=" + time +
                '}';
    }

    
}

