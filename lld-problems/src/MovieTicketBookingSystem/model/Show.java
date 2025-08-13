package MovieTicketBookingSystem.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Show {
    public int id;
    public int theatreId;
    public int hallId;
    public int movieId;
    public LocalTime startTime;
    public LocalDate showDate;

    public Show(int id, int theatreId, int hallId, int movieId, LocalTime startTime, LocalDate showDate) {
        this.id = id;
        this.theatreId = theatreId;
        this.hallId = hallId;
        this.movieId = movieId;
        this.startTime = startTime;
        this.showDate = showDate;
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", theatreId=" + theatreId +
                ", hallId=" + hallId +
                ", movieId=" + movieId +
                ", startTime=" + startTime +
                ", showDate=" + showDate +
                '}';
    }
}
