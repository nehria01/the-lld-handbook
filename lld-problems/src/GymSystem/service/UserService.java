package GymSystem.service;


import GymSystem.model.Admin;
import GymSystem.model.Customer;
import GymSystem.repository.AdminRepository;
import GymSystem.repository.CustomerRepository;

public class UserService {
    private static UserService userService = null;
    private AdminRepository adminRepository;
    private CustomerRepository customerRepository;

    public static UserService getInstance() {
        if(userService == null) {
            synchronized (UserService.class) {
                userService = new UserService();
            }
        }
        return userService;
    }

    private UserService() {
        this.adminRepository = AdminRepository.getInstance();
        this.customerRepository = CustomerRepository.getInstance();
    }

    public Integer createAdmin(String name) {
        Admin admin = new Admin(name);
        adminRepository.addAdmin(admin);
        return admin.getId();
    }

    public Integer createCustomer(String name) {
        Customer customer = new Customer(name);
        customerRepository.addCustomer(customer);
        return customer.getId();
    }
}
