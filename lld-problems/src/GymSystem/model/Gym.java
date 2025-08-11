package GymSystem.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Gym {
    private static AtomicInteger GYM_ID_GENERATOR = new AtomicInteger(1);
    private Integer id;
    private String name;
    private Integer adminId;
    private String location;
    private Integer maxCapacity;
    private Set<Integer> sessionIds;

    public Gym(String name, Integer adminId, String location, Integer maxCapacity) {
        this.id = GYM_ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.adminId = adminId;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.sessionIds = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public Set<Integer> getSessionIds() {
        return sessionIds;
    }

    public boolean addSessionId(Integer id) {
        return sessionIds.add(id);
    }

    public boolean removeSessionId(Integer id) {
        return sessionIds.remove(id);
    }
}
