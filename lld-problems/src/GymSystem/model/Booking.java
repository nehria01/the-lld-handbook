package GymSystem.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Booking {
    private static AtomicInteger BOOKING_ID_GENERATOR = new AtomicInteger(1);
    private Integer id;
    private Integer gymId;
    private Integer sessionId;
    private Integer customerId;

    public Booking(Integer gymId, Integer sessionId, Integer customerId) {
        this.id = BOOKING_ID_GENERATOR.getAndIncrement();
        this.gymId = gymId;
        this.sessionId = sessionId;
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGymId() {
        return gymId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public Integer getCustomerId() {
        return customerId;
    }
}
