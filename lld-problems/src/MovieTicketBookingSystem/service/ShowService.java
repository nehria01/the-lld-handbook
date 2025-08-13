package MovieTicketBookingSystem.service;

import MovieTicketBookingSystem.model.Show;
import MovieTicketBookingSystem.repository.ShowsRepository;

import java.time.LocalDate;
import java.util.List;

public class ShowService {
    private ShowsRepository showsRepository;

    public ShowService(ShowsRepository showsRepository) {
        this.showsRepository = showsRepository;
    }

    public List<Show> getAllShows(int theatreId, LocalDate localDate) {
        return showsRepository.getShows(theatreId, localDate);
    }

    public Show getShow(int showId) {
        return showsRepository.getShow(showId);
    }
}
