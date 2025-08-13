package MovieTicketBookingSystem.service;

import GymSystem.repository.AdminRepository;
import MovieTicketBookingSystem.model.Show;
import MovieTicketBookingSystem.repository.BookingsRepository;
import MovieTicketBookingSystem.repository.ShowsRepository;
import MovieTicketBookingSystem.util.IdGenerator;

import java.time.LocalDate;
import java.time.LocalTime;

public class AdminService {
    private AdminRepository adminRepository;
    private ShowsRepository showsRepository;
    private BookingsRepository bookingsRepository;

    public AdminService(AdminRepository adminRepository, ShowsRepository showsRepository, BookingsRepository bookingsRepository) {
        this.adminRepository = adminRepository;
        this.showsRepository = showsRepository;
        this.bookingsRepository = bookingsRepository;
    }

    public void addShow(int theatreId, int movieId, int hallId, LocalTime startTime, LocalDate showDate) {
        int id = IdGenerator.getId();
        Show show = new Show(id, theatreId, hallId, movieId, startTime, showDate);
        showsRepository.addShow(show);
    }


}
