package GymSystem.service;


import GymSystem.model.Booking;
import GymSystem.repository.BookingRepository;

import java.util.List;

public class BookingService {
    private static BookingService instance = null;
    private BookingRepository bookingRepository;

    public static BookingService getInstance() {
        if(instance == null) {
            synchronized (BookingService.class) {
                instance = new BookingService();
            }
        }
        return instance;
    }

    private BookingService() {
        this.bookingRepository = BookingRepository.getInstance();
    }

    public List<Booking> getAllBookingsForGivenSession(Integer sessionid) {
        return bookingRepository.getAllBookingsForSessionId(sessionid);
    }

    public void removeBooking(Integer bookingId) {
        bookingRepository.removeBooking(bookingId);
    }

    public Booking addBooking(Integer customerId, Integer gymId, Integer sessionId) {
        Booking booking = new Booking(gymId,sessionId,customerId);
        bookingRepository.addBooking(booking);
        return booking;
    }
}
