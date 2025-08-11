package GymSystem.repository;



import GymSystem.model.Gym;

import java.util.HashMap;

public class GymRepository {
    private static GymRepository gymRepository = null;

    public static GymRepository getInstance() {
        if(gymRepository == null) {
            synchronized (GymRepository.class) {
                if(gymRepository == null)
                    gymRepository = new GymRepository();
            }
        }
        return gymRepository;
    }

    private HashMap<Integer, Gym> gymMap;
    private GymRepository() {
        this.gymMap = new HashMap<>();
    }

    public void addGym(Gym gym) {
        gymMap.put(gym.getId(), gym);
    }

    public void removeGym(Integer gymId) {
        gymMap.remove(gymId);
    }

    public Gym getGym(Integer id) {
        return gymMap.getOrDefault(id, null);
    }

}
