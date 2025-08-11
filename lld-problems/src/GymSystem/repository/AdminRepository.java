package GymSystem.repository;


import GymSystem.model.Admin;

import java.util.HashMap;

public class AdminRepository {
    private static AdminRepository adminRepository = null;

    public static AdminRepository getInstance() {
        if(adminRepository == null) {
            synchronized (AdminRepository.class) {
                if(adminRepository == null)
                    adminRepository = new AdminRepository();
            }
        }
        return adminRepository;
    }

    private HashMap<Integer, Admin> adminMap;
    private AdminRepository() {
        this.adminMap = new HashMap<>();
    }

    public void addAdmin(Admin admin) {
        adminMap.put(admin.getId(), admin);
    }

    public void removeAdmin(Admin admin) {
        adminMap.remove(admin.getId());
    }

    public Admin getAdmin(Integer id) {
        return adminMap.getOrDefault(id, null);
    }
}
