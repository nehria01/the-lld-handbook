package MovieTicketBookingSystem.repository;

import MovieTicketBookingSystem.model.Seat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeatRepository {
    private static SeatRepository instance;
    private Map<String, Seat> seatsMap;

    private SeatRepository() {
        seatsMap = new HashMap<>();
    }

    public static SeatRepository getInstance() {
        if (instance == null) {
            synchronized (SeatRepository.class) {
                if (instance == null) {
                    instance = new SeatRepository();
                }
            }
        }
        return instance;
    }

    private String generateKey(int theatreId, int hallId, int row, int col) {
        return theatreId + "_" + hallId + "_" + row + "_" + col;
    }

    public void addSeat(Seat seat) {
        String key = generateKey(seat.theatreId, seat.hallId, seat.row, seat.col);
        seatsMap.put(key, seat);
    }

    public Seat getSeat(int theatreId, int hallId, int row, int col) {
        String key = generateKey(theatreId, hallId, row, col);
        return seatsMap.get(key);
    }

    public List<Seat> getSeatsByTheatreAndHall(int theatreId, int hallId) {
        return seatsMap.values().stream()
                .filter(seat -> seat.theatreId == theatreId && seat.hallId == hallId)
                .collect(Collectors.toList());
    }

    public void bookSeat(String seatKey) {
        Seat seat = seatsMap.get(seatKey);
        if (seat == null) {
            System.out.println("Seat not found");
        }
        if (!seat.isAvailable) {
            System.out.println("Seat already booked");
        }
        seat.isAvailable = false;
    }

    public List<Seat> getAllSeats() {
        return seatsMap.values().stream().toList();
    }
}