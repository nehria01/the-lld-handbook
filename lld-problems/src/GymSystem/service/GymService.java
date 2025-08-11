package GymSystem.service;


import GymSystem.model.Gym;
import GymSystem.repository.GymRepository;

public class GymService {
    private static GymService instance;
    private GymRepository gymRepository;

    public static GymService getInstance() {
        if(instance == null) {
            synchronized (GymService.class) {
                instance = new GymService();
            }
        }
        return instance;
    }

    private GymService() {
        this.gymRepository = GymRepository.getInstance();
    }

    public Integer addGym(String name, Integer adminId, String location, Integer maxCapacity) {
        Gym gym = new Gym(name, adminId, location, maxCapacity);
        gymRepository.addGym(gym);
        return gym.getId();
    }

    public void removeGym(Integer gymId) {
        gymRepository.removeGym(gymId);
    }

    public Gym getGym(Integer gymId) {
        return gymRepository.getGym(gymId);
    }
}
