package MovieTicketBookingSystem.repository;

import MovieTicketBookingSystem.model.Admin;
import MovieTicketBookingSystem.model.Customer;
import MovieTicketBookingSystem.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static UserRepository instance = null;
    private Map<Integer, Admin> adminMap;
    private Map<Integer, Customer> customerMap;

    private UserRepository() {
        this.adminMap = new HashMap<>();
        this.customerMap = new HashMap<>();
    }

    public static UserRepository getInstance() {
        if(instance == null) {
            synchronized(UserRepository.class) {
                if(instance == null)
                    instance = new UserRepository();
            }
        }
        return instance;
    }

    public void addAdmin(Admin admin) {
        this.adminMap.put(admin.id, admin);
    }

    public void addCustomer(Customer customer) {
        this.customerMap.put(customer.id, customer);
    }

    public Admin getAdmin(int id) {
        return this.adminMap.getOrDefault(id, null);
    }

    public Customer getCustomer(int id) {
        return this.customerMap.getOrDefault(id, null);
    }

}
