package GymSystem.repository;



import GymSystem.model.Customer;

import java.util.HashMap;

public class CustomerRepository {
    private static CustomerRepository customerRepository = null;

    public static CustomerRepository getInstance() {
        if(customerRepository == null) {
            synchronized (CustomerRepository.class) {
                if(customerRepository == null)
                    customerRepository = new CustomerRepository();
            }
        }
        return customerRepository;
    }

    private HashMap<Integer, Customer> customerMap;
    private CustomerRepository() {
        this.customerMap = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customerMap.put(customer.getId(), customer);
    }

    public void removeCustomer(Customer customer) {
        customerMap.remove(customer.getId());
    }

    public Customer getCustomer(Integer id) {
        return customerMap.getOrDefault(id, null);
    }
}
