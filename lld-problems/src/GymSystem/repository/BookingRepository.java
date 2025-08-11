package GymSystem.repository;



import GymSystem.model.Booking;

import java.util.HashMap;
import java.util.List;

public class BookingRepository {
    private static BookingRepository bookingRepository = null;

    public static BookingRepository getInstance() {
        if(bookingRepository == null) {
            synchronized (BookingRepository.class) {
                if(bookingRepository == null)
                    bookingRepository = new BookingRepository();
            }
        }
        return bookingRepository;
    }

    private HashMap<Integer, Booking> bookingsMap;
    private BookingRepository() {
        this.bookingsMap = new HashMap<>();
    }

    public void addBooking(Booking booking) {
        bookingsMap.put(booking.getId(), booking);
    }

    public Booking removeBooking(Booking booking) {
        return bookingsMap.remove(booking.getId());
    }

    public Booking getBooking(Integer id) {
        return bookingsMap.getOrDefault(id, null);
    }

    public List<Booking> getAllBookingsForSessionId(Integer sessionId) {
        return bookingsMap.values().stream()
                .filter(b -> b.getSessionId().equals(sessionId))
                .toList();
    }

    public void removeBooking(Integer bookingId) {
        bookingsMap.remove(bookingId);
    }
}
