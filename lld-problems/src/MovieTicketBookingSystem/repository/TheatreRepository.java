package MovieTicketBookingSystem.repository;
import MovieTicketBookingSystem.model.Theatre;
import MovieTicketBookingSystem.util.IdGenerator;

import java.util.HashMap;
import java.util.Map;

public class TheatreRepository {
    private static TheatreRepository instance = null;
    private Map<Integer, Theatre> theatreMap;

    private TheatreRepository() {
        this.theatreMap = new HashMap<>();
    }

    public static TheatreRepository getInstance() {
        if(instance == null) {
            synchronized(TheatreRepository.class) {
                if(instance == null)
                    instance = new TheatreRepository();
            }
        }
        return instance;
    }

    public void addTheatre(Theatre theatre) {
        this.theatreMap.put(theatre.id, theatre);
    }

    public Theatre getTheatre(int id) {
        return this.theatreMap.getOrDefault(id, null);
    }

}
