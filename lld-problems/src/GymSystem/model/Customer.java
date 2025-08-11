package GymSystem.model;


import GymSystem.enums.UserType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customer extends User{
    private Set<Integer> bookingIds;

    public Customer(String name) {
        super(UserType.CUSTOMER, name);
        this.bookingIds = new HashSet<>();
    }

    public void removeBooking(Integer bookingId) {
        bookingIds.remove(bookingId);
    }

    public List<Integer> getBookedSessionIds() {
        return bookingIds.stream().toList();
    }

    public void addBookingId(Integer id) {
        bookingIds.add(id);
    }
}
