package GymSystem.service;

import GymSystem.enums.SessionType;
import GymSystem.model.Booking;
import GymSystem.model.Gym;
import GymSystem.model.GymSession;
import GymSystem.repository.GymSessionRespository;

import java.time.LocalDate;
import java.util.List;

public class GymSessionService {
    private static GymSessionService instance;
    private GymSessionRespository respository;
    private GymService gymService;
    private BookingService bookingService;
    private CustomerService customerService;
    public static GymSessionService getInstance() {
        if(instance == null) {
            synchronized (GymSessionService.class) {
                instance = new GymSessionService();
            }
        }
        return instance;
    }

    private GymSessionService() {
        this.respository = GymSessionRespository.getInstance();
    }

    /**
     *
     * @param gymId
     * @param sessionDate
     * @param startHour
     * @param endHour
     * @param sessionType
     * @param maxLimit
     *
     * validate if gymId exists
     * validate max limit at gym level
     * store the gymSession in the repository
     * add the session id to the gym class
     */
    public Integer addSession(Integer gymId, LocalDate sessionDate, Integer startHour,
                              Integer endHour, SessionType sessionType, Integer maxLimit) {
        Gym gym = GymService.getInstance().getGym(gymId);
        if(!validateGymSession(gym, sessionDate, startHour, endHour, maxLimit))
            return null;

        GymSession gymSession = new GymSession(gymId, sessionDate, startHour, endHour, sessionType, maxLimit);
        respository.addGymSession(gymSession);
        gym.addSessionId(gymSession.getId());
        return gymSession.getId();
    }

    private boolean validateGymSession(Gym gym, LocalDate sessionDate, Integer startHour,
                                       Integer endHour, Integer maxLimit) {

        if(gym == null) {
            System.out.println("Gym does not exist");
            return false;
        }

        List<GymSession> sessionOnDate = gym.getSessionIds().stream()
                .map(id -> respository.getGymSession(id))
                .filter(s-> s.getSessionDate().isEqual(sessionDate))
                .toList();

        int totalCapacity = 0;
        for(var existingSession : sessionOnDate) {
            if(startHour < existingSession.getEndHour()
                    && endHour > existingSession.getStartHour())
                totalCapacity += existingSession.getMaxLimit();
        }

        if(totalCapacity+maxLimit> gym.getMaxCapacity())
            return false;
        return true;
    }

    /**
     *
     * @param sessionId
     * remove gym session from the data store
     * remove the corresponding gym session id from the given gym
     * remove all bookings for this given session
     */
    public void removeSession(Integer sessionId) {
        GymSession gymSession = respository.removeGymSession(sessionId);
        if(gymSession!=null) {
            Gym gym =  GymService.getInstance().getGym(gymSession.getGymId());
            gym.removeSessionId(gymSession.getId());
        }
        List<Booking> bookingIds = BookingService.getInstance().getAllBookingsForGivenSession(sessionId);
        bookingIds.forEach(b -> {
            BookingService.getInstance().removeBooking(b.getId());
            CustomerService.getInstance().removeBooking(b.getCustomerId(), b.getId());
        });
    }

    public GymSession getGymSession(Integer sessionId) {
        return respository.getGymSession(sessionId);
    }
}
