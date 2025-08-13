package MovieTicketBookingSystem.service;
import MovieTicketBookingSystem.model.Booking;
import MovieTicketBookingSystem.model.Customer;
import MovieTicketBookingSystem.model.Seat;
import MovieTicketBookingSystem.model.Show;
import MovieTicketBookingSystem.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CustomerService {
    private UserRepository userRepository;
    private SeatService seatService;
    private ShowService showService;
    private BookingService bookingService;

    public CustomerService(UserRepository userRepository, SeatService seatService, ShowService showService, BookingService bookingService) {
        this.userRepository = userRepository;
        this.seatService = seatService;
        this.showService = showService;
        this.bookingService = bookingService;
    }

    public void addCustomer(Customer customer) {
        userRepository.addCustomer(customer);
    }

    public void viewAllShows(int theatreId, LocalDate localDate) {
        List<Show> showsList = showService.getAllShows(theatreId, localDate);
        showsList.forEach(System.out::println);
    }

    public void viewSeats(int theatreId, int hallId, int showId) {
        List<Seat> seats = seatService.getSeatMap(theatreId, hallId);

        System.out.println("Seat Map for Theatre " + theatreId + ", Hall " + hallId + ", Show " + showId + ":");
        seats.stream()
                .sorted((s1, s2) -> s1.row == s2.row ? s1.col - s2.col : s1.row - s2.row)
                .forEach(seat -> {
                    String status = seat.isAvailable ? "Available" : "Booked";
                    System.out.println("Row: " + seat.row + ", Col: " + seat.col + ", Price: " + seat.price + ", Status: " + status);
                });
    }

    public String createBooking(int customerId, int theatreId, int hallId, int showId, int row, int col, int totalAmount) {
        Customer customer = userRepository.getCustomer(customerId);
        if(customer == null) {
            System.out.println("Customer does not exist");
            return null;
        }

        Booking booking = bookingService.bookSeats(customerId, theatreId, hallId, showId, row, col, totalAmount);
        if(Objects.nonNull(booking)) {
            System.out.println("Booking successful! ID: " + booking.id);
            return booking.id;
        }
        return null;
    }

    public void getAllBookings(int customerId) {
        List<Booking> bookings = bookingService.getAllBookingsForCustomer(customerId);
        bookings.forEach(System.out::println);
    }

    public void getBooking(int customerId, String bookingId) {
        Booking booking = bookingService.getBooking(bookingId);
        System.out.println(bookingId);
    }
}
