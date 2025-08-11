package GymSystem.model;

import GymSystem.enums.UserType;

public class Admin extends User{

    public Admin(String name) {
        super(UserType.ADMIN, name);
    }
}
