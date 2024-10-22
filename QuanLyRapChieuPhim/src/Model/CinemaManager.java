/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ASUS
 */
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class CinemaManager {
    private List<Movie> movies = new ArrayList<>();
    private List<Showtime> showtimes = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();

    // Quản lý phim
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    // Quản lý suất chiếu
    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }

    public void removeShowtime(Showtime showtime) {
        showtimes.remove(showtime);
    }

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    // Quản lý vé
    public void bookTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}


