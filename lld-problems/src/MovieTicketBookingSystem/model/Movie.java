package MovieTicketBookingSystem.model;

import java.time.LocalDate;

public class Movie {
    public int id;
    public String name;
    public String description;
    public LocalDate releaseDate;
    public int duration;

    public Movie(int id, String name, String description, LocalDate releaseDate, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}
