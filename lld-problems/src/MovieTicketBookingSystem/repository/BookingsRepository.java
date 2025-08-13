package MovieTicketBookingSystem.repository;

import MovieTicketBookingSystem.model.Booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingsRepository {
    private static BookingsRepository instance = null;
    private Map<String, Booking> bookingsMap;

    private BookingsRepository() {
        this.bookingsMap = new HashMap<>();
    }

    public static BookingsRepository getInstance() {
        if(instance == null) {
            synchronized(BookingsRepository.class) {
                if(instance == null)
                    instance = new BookingsRepository();
            }
        }
        return instance;
    }

    public void addBooking(Booking booking) {
        this.bookingsMap.put(booking.id, booking);
    }

    public Booking getBooking(String id) {
        return this.bookingsMap.getOrDefault(id, null);
    }

    public List<Booking> getBookingForCustomer(int customerId) {
        return this.bookingsMap.values().stream().filter(b -> b.customerId == customerId).toList();
    }
}
