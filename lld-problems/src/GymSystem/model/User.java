package GymSystem.model;



import GymSystem.enums.UserType;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class User {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    private UserType userType;
    private Integer id;
    private String name;

    public User(UserType userType, String name) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.userType = userType;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
}
