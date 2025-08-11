package GymSystem.service;



import GymSystem.enums.SessionType;
import GymSystem.model.Admin;
import GymSystem.repository.AdminRepository;

import java.time.LocalDate;

public class AdminService {
    private static AdminService instance = null;
    private AdminRepository adminRepository;
    private GymService gymService;
    private GymSessionService gymSessionService;

    public static AdminService getInstance() {
        if(instance == null) {
            synchronized (AdminService.class) {
                instance = new AdminService();
            }
        }
        return instance;
    }

    private AdminService() {
        this.adminRepository = AdminRepository.getInstance();

    }
    public Integer addGym(String name, Integer adminId, String location, Integer maxCapacity) {
        Admin admin = adminRepository.getAdmin(adminId);
        if(admin == null)
            throw new RuntimeException("Admin does not exist");
        Integer gymId = GymService.getInstance().addGym(name, adminId, location, maxCapacity);
        return gymId;
    }

    public void removeGym(Integer gymId) {
        GymService.getInstance().removeGym(gymId);
    }

    public Integer addGymSession(Integer gymId, LocalDate sessionDate, Integer startHour,
                                 Integer endHour, SessionType sessionType, Integer maxLimit) {
        return GymSessionService.getInstance().addSession(gymId, sessionDate, startHour, endHour, sessionType, maxLimit);
    }

    public void removeGymSession(Integer sessionId) {
        GymSessionService.getInstance().removeSession(sessionId);
    }
}
