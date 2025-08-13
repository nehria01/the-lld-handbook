package MovieTicketBookingSystem.repository;
import MovieTicketBookingSystem.model.Movie;
import MovieTicketBookingSystem.model.Show;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowsRepository {
    private static ShowsRepository instance = null;
    private Map<Integer, Show> showsMap;

    private ShowsRepository() {
        this.showsMap = new HashMap<>();
    }

    public static ShowsRepository getInstance() {
        if(instance == null) {
            synchronized(ShowsRepository.class) {
                if(instance == null)
                    instance = new ShowsRepository();
            }
        }
        return instance;
    }

    public void addShow(Show show) {
        this.showsMap.put(show.id, show);
    }

    public Show getShow(int id) {
        return this.showsMap.getOrDefault(id, null);
    }

    public List<Show> getShows(int theatreId, LocalDate localDate) {
        List<Show> res;
        res =  showsMap.values().stream().filter(s -> s.theatreId == theatreId &&
                s.showDate .equals(localDate)).toList();
        return res;
    }
}
