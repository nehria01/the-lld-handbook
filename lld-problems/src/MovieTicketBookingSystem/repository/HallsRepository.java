package MovieTicketBookingSystem.repository;

import MovieTicketBookingSystem.model.Hall;
import MovieTicketBookingSystem.util.IdGenerator;

import java.util.HashMap;
import java.util.Map;

public class HallsRepository {
    private static HallsRepository instance = null;
    private Map<Integer, Hall> hallsMap;

    private HallsRepository() {
        this.hallsMap = new HashMap<>();
    }

    public static HallsRepository getInstance() {
        if(instance == null) {
            synchronized(HallsRepository.class) {
                if(instance == null)
                    instance = new HallsRepository();
            }
        }
        return instance;
    }

    public void addHall(Hall hall) {
        this.hallsMap.put(hall.id, hall);
    }

    public Hall getHall(int id) {
        return this.hallsMap.getOrDefault(id, null);
    }
}
