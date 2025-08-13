package MovieTicketBookingSystem.service;

import MovieTicketBookingSystem.model.Booking;
import MovieTicketBookingSystem.model.Seat;
import MovieTicketBookingSystem.repository.BookingsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingService {
    private BookingsRepository bookingsRepository;
    private SeatService seatService;
    public BookingService(BookingsRepository bookingsRepository, SeatService seatService) {
        this.bookingsRepository = bookingsRepository;
        this.seatService = seatService;
    }

    public synchronized Booking bookSeats(int customerId, int theatreId, int hallId, int showId,
                                          int row, int col, double totalAmount) {
        Seat  seatToBook = seatService.getSeat(theatreId, hallId, row, col);;

        double expectedAmount = seatToBook.price;
        if (Double.compare(expectedAmount, totalAmount) != 0) {
            System.out.println("Amount mismatch. Expected: " + expectedAmount + ", Provided: " + totalAmount);
            return null;
        }

       if(!seatService.bookSeat(seatToBook)) {
           return null;
       }

        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking(
                bookingId.toString(),
                theatreId,
                hallId,
                showId,
                seatToBook,
                totalAmount,
                customerId,
                LocalDateTime.now()
        );

        bookingsRepository.addBooking(booking);
        return booking;
    }


    List<Booking> getAllBookingsForCustomer(int customerId) {
        return bookingsRepository.getBookingForCustomer(customerId);
    }

    Booking getBooking(String bookingId) {
        return bookingsRepository.getBooking(bookingId);
    }

}