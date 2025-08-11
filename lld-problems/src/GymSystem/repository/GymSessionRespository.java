package GymSystem.repository;



import GymSystem.model.GymSession;

import java.util.HashMap;

public class GymSessionRespository {
    private static GymSessionRespository gymSessionRespository = null;

    public static GymSessionRespository getInstance() {
        if(gymSessionRespository == null) {
            synchronized (GymSessionRespository.class) {
                if(gymSessionRespository == null)
                    gymSessionRespository = new GymSessionRespository();
            }
        }
        return gymSessionRespository;
    }

    private HashMap<Integer, GymSession> sessionMap;
    private GymSessionRespository() {
        this.sessionMap = new HashMap<>();
    }

    public void addGymSession(GymSession gymSession) {
        sessionMap.put(gymSession.getId(), gymSession);
    }

    public GymSession  removeGymSession(Integer gymSessionId) {
        return sessionMap.remove(gymSessionId);
    }

    public GymSession getGymSession(Integer id) {
        return sessionMap.getOrDefault(id, null);
    }
}
