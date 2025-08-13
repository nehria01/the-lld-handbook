package MovieTicketBookingSystem.model;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    public String id;  // previously int
    public int theatreId;
    public int hallId;
    public int showId;
    public Seat bookedSeat;
    public double amount;
    public int customerId;
    public LocalDateTime bookingTime;

    public Booking(String id, int theatreId, int hallId, int showId,
                   Seat seat, double amount, int customerId, LocalDateTime bookingTime) {
        this.id = id;
        this.theatreId = theatreId;
        this.hallId = hallId;
        this.showId = showId;
        this.bookedSeat = seat;
        this.amount = amount;
        this.customerId = customerId;
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", theatreId=" + theatreId +
                ", hallId=" + hallId +
                ", showId=" + showId +
                ", bookedSeat=" + bookedSeat +
                ", amount=" + amount +
                ", customerId=" + customerId +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
