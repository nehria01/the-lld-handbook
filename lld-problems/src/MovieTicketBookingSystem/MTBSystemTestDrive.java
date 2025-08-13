package MovieTicketBookingSystem;

import GymSystem.repository.CustomerRepository;
import MovieTicketBookingSystem.model.*;
import MovieTicketBookingSystem.repository.*;
import MovieTicketBookingSystem.service.BookingService;
import MovieTicketBookingSystem.service.CustomerService;
import MovieTicketBookingSystem.service.SeatService;
import MovieTicketBookingSystem.service.ShowService;
import MovieTicketBookingSystem.util.IdGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class MTBSystemTestDrive {
    private static int initTheatre(TheatreRepository theatreRepository) {
        Theatre theatre = new Theatre(
                IdGenerator.getId(),
                "PVR Cinemas",
                "Connaught Place, Delhi",
                1
        );
        theatreRepository.addTheatre(theatre);
        System.out.println("Added Theatre with id :: " + theatre.id);
        return theatre.id;
    }

    private static List<Integer> initHalls(int theatreId, HallsRepository hallsRepository) {
        Hall hall1 = new Hall(
                IdGenerator.getId(), // unique hall ID
                theatreId,                  // theatreId
                1,                  // number of rows
                5                   // number of columns
        );

        Hall hall2 = new Hall(
                IdGenerator.getId(), // unique hall ID
                theatreId,                  // theatreId
                1,                   // number of rows
                5                   // number of columns
        );
        hallsRepository.addHall(hall1);
        hallsRepository.addHall(hall2);

        List<Integer> hallIds = new LinkedList<>();
        hallIds.addLast(hall1.id);
        hallIds.addLast(hall2.id);
        return hallIds;
    }

    private static void initSeats(int theatreId, int hall1Id, int hall2Id, SeatRepository seatRepository) {
        // Let's assume a base price rule — e.g., row 1-3 premium, rest standard
        double premiumPrice = 300.0;
        double standardPrice = 200.0;

        // Hall 1: 10 rows, 15 cols
        for (int row = 1; row <= 1; row++) {
            for (int col = 1; col <= 5; col++) {
                double price = (row <= 3) ? premiumPrice : standardPrice;
                Seat seat = new Seat(
                        IdGenerator.getId(),
                        theatreId,
                        hall1Id,
                        row,
                        col,
                        price
                );
                seatRepository.addSeat(seat);
            }
        }

        // Hall 2: 8 rows, 12 cols
        for (int row = 1; row <= 1; row++) {
            for (int col = 1; col <= 5; col++) {
                double price = (row <= 2) ? premiumPrice : standardPrice;
                Seat seat = new Seat(
                        IdGenerator.getId(),
                        theatreId,
                        hall2Id,
                        row,
                        col,
                        price
                );
                seatRepository.addSeat(seat);
            }
        }
    }

    private static void initShows(int theatreId, int hall1Id, int hall2Id, ShowsRepository showRepository, MovieRepository movieRepository) {
        Movie movie1 = new Movie(
                IdGenerator.getId(),
                "Oppenheimer",
                "A biographical drama about J. Robert Oppenheimer, the father of the atomic bomb.",
                LocalDate.of(2023, 7, 21),
                180 // in minutes
        );
        movieRepository.addMovie(movie1);
        Movie movie2 = new Movie(
                IdGenerator.getId(),
                "Interstellar",
                "A team of explorers travels through a wormhole in space to ensure humanity's survival.",
                LocalDate.of(2014, 11, 7),
                169
        );
        movieRepository.addMovie(movie2);
        Movie movie3 = new Movie(
                IdGenerator.getId(),
                "Inception",
                "A skilled thief is given a chance at redemption if he can successfully perform inception.",
                LocalDate.of(2010, 7, 16),
                148
        );
        movieRepository.addMovie(movie3);
        Movie movie4 = new Movie(
                IdGenerator.getId(),
                "The Dark Knight",
                "Batman faces his greatest psychological and physical test when the Joker wreaks havoc on Gotham.",
                LocalDate.of(2025, 8, 13),
                152
        );
        movieRepository.addMovie(movie4);
        // Hall 1 shows
        showRepository.addShow(new Show(
                IdGenerator.getId(),
                theatreId,
                hall1Id,
                movie1.id,
                LocalTime.of(10,0,0),
                LocalDate.of(2025, 8, 13)
        ));
        showRepository.addShow(new Show(
                IdGenerator.getId(),
                theatreId,
                hall1Id,
                movie2.id,
                LocalTime.of(15,0,0),
                LocalDate.of(2025, 8, 13)
        ));

        // Hall 2 shows
        showRepository.addShow(new Show(
                IdGenerator.getId(),
                theatreId,
                hall2Id,
                movie3.id,
                LocalTime.of(11,0,0),
                LocalDate.of(2025, 8, 13)
        ));

        showRepository.addShow(new Show(
                IdGenerator.getId(),
                theatreId,
                hall2Id,
                movie4.id,
                LocalTime.of(16,0,0),
                LocalDate.of(2025, 8, 13)
        ));
    }
    public static void main(String args[]) {
        TheatreRepository theatreRepository = TheatreRepository.getInstance();
        int theatreId = initTheatre(theatreRepository);

        HallsRepository hallsRepository = HallsRepository.getInstance();
        List<Integer> hallIds = initHalls(theatreId, hallsRepository);

        SeatRepository seatRepository = SeatRepository.getInstance();
        initSeats(theatreId, hallIds.get(0), hallIds.get(1), seatRepository);

        ShowsRepository showsRepository = ShowsRepository.getInstance();
        MovieRepository movieRepository = MovieRepository.getInstance();
        initShows(theatreId,hallIds.get(0), hallIds.get(1),showsRepository, movieRepository);

        UserRepository customerRepository = UserRepository.getInstance();
        SeatService seatService = new SeatService(seatRepository);
        BookingService bookingService = new BookingService(BookingsRepository.getInstance(), seatService);
        ShowService showService = new ShowService(showsRepository);
        CustomerService customerService = new CustomerService(customerRepository, seatService, showService,bookingService);
        customerService.viewAllShows(theatreId, LocalDate.now());
        customerService.viewSeats(1,2,256);
        Customer customer = new Customer(1234, "Aditya");
        customerService.addCustomer(customer);
        String bookingId = customerService.createBooking(customer.id, 1, 2, 256, 1, 1, 300);
        customerService.getAllBookings(customer.id);
    }
}
