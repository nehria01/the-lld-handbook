package GymSystem.service;



import GymSystem.model.Booking;
import GymSystem.model.Customer;
import GymSystem.model.Gym;
import GymSystem.model.GymSession;
import GymSystem.repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private static CustomerService instance;
    private CustomerRepository customerRepository;
    private GymService gymService;
    private GymSessionService gymSessionService;
    private BookingService bookingService;

    public static CustomerService getInstance() {
        if(instance == null) {
            synchronized (CustomerService.class) {
                instance = new CustomerService();
            }
        }
        return instance;
    }

    private CustomerService() {
        this.customerRepository = CustomerRepository.getInstance();
    }

    public Integer bookClass(Integer customerId, Integer gymId, Integer sessionId) {
        Customer customer = customerRepository.getCustomer(customerId);
        if(customer == null) {
            System.out.println("Customer does not exist");
            return null;
        }

        Gym gym = GymService.getInstance().getGym(gymId);
        if(gym == null) {
            System.out.println("Gym does not exist");
            return null;
        }

        GymSession gymSession = GymSessionService.getInstance().getGymSession(sessionId);
        if(gymSession == null) {
            System.out.println("GymSession does not exist");
            return null;
        } else if(gym.getId() != gymSession.getGymId()) {
            System.out.println("GymSession and Gym are not compatible");
            return null;
        }

        List<Integer> bookedSessionIds = customer.getBookedSessionIds();
        long overlappingSessions = bookedSessionIds.stream()
                .map(id -> GymSessionService.getInstance().getGymSession(id))
                .filter(s -> s!=null && s.getSessionDate().equals(gymSession.getSessionDate()))
                .filter(s -> gymSession.getStartHour() < s.getEndHour()
                        && gymSession.getEndHour() > s.getStartHour())
                .count();

        if(overlappingSessions>=1)  {
            System.out.println("Cannot book, another session present");
            return null;
        }

        if(gymSession.getBookingIds().size() >= gym.getMaxCapacity())
            return null;

        Booking booking = BookingService.getInstance().addBooking(customerId, gymId, sessionId);
        customer.addBookingId(booking.getId());
        return booking.getId();
    }

    public void removeBooking(Integer customerId, Integer bookingId) {
        Customer customer = customerRepository.getCustomer(customerId);
        customer.removeBooking(bookingId);
    }

    public List<Integer> getAllBookings(Integer customerId) {
        return customerRepository.getCustomer(customerId).getBookedSessionIds().stream().toList();
    }
}
