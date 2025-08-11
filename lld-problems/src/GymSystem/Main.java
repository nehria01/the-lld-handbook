package GymSystem;

import GymSystem.enums.SessionType;
import GymSystem.service.*;


import java.time.LocalDate;

public class Main {
    /**
     *
     * Admin Flow -
     *  add gym
     *      - max capacity must be maintained at any given time
     *      - multiple classes from 6 am - 8 pm
     *  remove gym
     *      - cancels all classes and bookings
     *  add class
     *      - need to check constraint on max people
     *          while adding class check any classes with overlapping times
     *      - timings should be within open hours
     *  remove class
     *      - removes class from gym
     *
     */

    /**
     * Models
     *      User
     *          id
     *          name
     *          userType
     *      Admin
     *          gymId
     *      Customer
     *          set<Integer> bookedSessionIds
     *      Gym
     *          id
     *          name
     *          location
     *          maxCapacity
     *          set<Integer> sessionIds
     *          adminId
     *      GymSession
     *          id
     *          gymId
     *          sessionType
     *          date
     *          startHour
     *          endHour
     *          maxLimit
     *          sessionType
     *          set<Integer> bookingIds
     *
     *      SessionType
     *
     *      UserType
     *          ADMIN / CUSTOMER
     *
     * Service
     *        UserService
     *          createAdmin
     *              - creates admin
     *              - adds to admin repo
     *          createCustomer
     *              - creates customer
     *              - adds to customer repo
     *
     *        GymService
     *          addGym
     *          removeGym
     *              - removes all sessions from session repo and each users
     *          getGym
     *
     *        GymSessionService
     *          addGymSession
     *          removeGymSession
     *
     * Repository
     *      AdminRespository
     *          adminMap
     *      CustomerRespository
     *          customerMap
     *      GymSessionRepository
     *          sessionMap
     *      GymRepository
     *          gymMap
     *      BookingsRespository
     *          bookingsMap
     */

    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        AdminService adminService = AdminService.getInstance();
        CustomerService customerService = CustomerService.getInstance();
        GymService gymService = GymService.getInstance();
        GymSessionService gymSessionService = GymSessionService.getInstance();

        //Create Admin & Customers
        Integer adminId = userService.createAdmin("Alice Admin");
        Integer cust1Id = userService.createCustomer("Charlie Customer");
        Integer cust2Id = userService.createCustomer("Bob Booker");

        // Add a Gym
        Integer gymId = adminService.addGym("Flex Gym", adminId, "Downtown", 10);

        // Add Sessions
        Integer yogaMorningId = adminService.addGymSession(
                gymId, LocalDate.now(), 9, 10, SessionType.YOGA, 5
        );
        Integer yogaNoonId = adminService.addGymSession(
                gymId, LocalDate.now(), 12, 13, SessionType.YOGA, 5
        );

        // Attempt overlapping session with capacity exceeding gym's total
        Integer heavyOverlapId = adminService.addGymSession(
                gymId, LocalDate.now(), 9, 11, SessionType.CARDIO, 8
        ); // Should fail if capacity rule triggers

        //Book valid sessions
        Integer booking1 = customerService.bookClass(cust1Id, gymId, yogaMorningId);
        System.out.println("Booking 1 ID: " + booking1);

        //Attempt to book overlapping session for same customer
        Integer bookingOverlap = customerService.bookClass(cust1Id, gymId, yogaMorningId); // exact same
        System.out.println("Overlap booking ID: " + bookingOverlap); // Should be null

        // Another customer books same session
        Integer booking2 = customerService.bookClass(cust2Id, gymId, yogaMorningId);
        System.out.println("Booking 2 ID: " + booking2);

        System.out.println("All bookings for Customer 1 before removal: " +
                customerService.getAllBookings(cust1Id));

        // Remove session and verify bookings removed
        adminService.removeGymSession(yogaMorningId);
        System.out.println("All bookings for Customer 1 after removal: " +
                customerService.getAllBookings(cust1Id));

        // Book after removal
        Integer booking3 = customerService.bookClass(cust1Id, gymId, yogaNoonId);
        System.out.println("Booking 3 ID: " + booking3);
    }
}