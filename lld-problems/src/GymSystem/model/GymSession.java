package GymSystem.model;



import GymSystem.enums.SessionType;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class GymSession {
    private static AtomicInteger GYM_SESSION_ID_GENERATOR = new AtomicInteger(1);
    private Integer id;
    private Integer gymId;
    private LocalDate sessionDate;
    private Integer startHour;
    private Integer endHour;
    private SessionType sessionType;
    private Integer maxLimit;
    private Set<Integer> bookingIds;

    public GymSession(Integer gymId, LocalDate sessionDate, Integer startHour,
                      Integer endHour, SessionType sessionType, Integer maxLimit) {
        this.id = GYM_SESSION_ID_GENERATOR.getAndIncrement();
        this.gymId = gymId;
        this.sessionDate = sessionDate;
        this.startHour = startHour;
        this.endHour = endHour;
        this.sessionType = sessionType;
        this.maxLimit = maxLimit;
        this.bookingIds = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public Integer getGymId() {
        return gymId;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public Set<Integer> getBookingIds() {
        return bookingIds;
    }

    public boolean addBookingId(int bookingId) {
        return bookingIds.add(bookingId);
    }

    public boolean removeBookingId(int bookingId) {
        return bookingIds.remove(bookingId);
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public Integer getEndHour() {
        return endHour;
    }
}
