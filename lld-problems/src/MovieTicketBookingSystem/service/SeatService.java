package MovieTicketBookingSystem.service;

import MovieTicketBookingSystem.model.Seat;
import MovieTicketBookingSystem.repository.SeatRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SeatService {
    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getSeatMap(int theatreId, int hallId) {
        return seatRepository.getAllSeats().stream()
                .filter(seat -> seat.theatreId == theatreId && seat.hallId == hallId)
                .collect(Collectors.toList());
    }

    public Seat getSeat(int theatreId, int hallId, int row, int col) {
        Seat seat = seatRepository.getSeat(theatreId, hallId, row, col);
        return seat;
    }
    public double getSeatPrice(int theatreId, int hallId, int row, int col) {
        Seat seat = seatRepository.getSeat(theatreId, hallId, row, col);
        if (seat == null) throw new RuntimeException("Seat not found");
        return seat.price;
    }

    public synchronized boolean bookSeat(Seat seat) {
        if(!seat.isAvailable) {
            System.out.println("Seat already booked");
            return false;
        }
        seat.isAvailable = true;
        return true;
    }
}
