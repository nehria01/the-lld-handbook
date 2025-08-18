package CarRentalSystem.repository;

import CarRentalSystem.enums.ReservationStatus;
import CarRentalSystem.enums.VehicleType;
import CarRentalSystem.exception.ValidationException;
import CarRentalSystem.model.Reservation;
import CarRentalSystem.model.Store;
import CarRentalSystem.model.User;
import CarRentalSystem.model.vehicle.HatchBack;
import CarRentalSystem.model.vehicle.SUV;
import CarRentalSystem.model.vehicle.Sedan;
import CarRentalSystem.model.vehicle.Vehicle;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private Map<Integer, Store> storeMap;
    private Map<Integer, User> userMap;
    private Map<String, Vehicle> vehicleMap;
    private Map<String, Reservation> reservationMap;

    private static Repository instance = null;
    public static Repository getInstance() {
        if(instance == null) {
            synchronized (Repository.class) {
                if(instance == null)
                    instance = new Repository();
            }
        }
        return instance;
    }
    private void initData() {
        Store store = new Store("Krishna Motors", 1);
        Sedan vehicle1 = new Sedan(VehicleType.SEDAN, 1, "HP39D1123", 1);
        SUV vehicle2 = new SUV(VehicleType.SUV, 2, "HP39D1124", 1);
        HatchBack vehicle3 = new HatchBack(VehicleType.HATCHBACK, 3, "HP39D1125", 1);

        storeMap.put(1,store);
        vehicleMap.put(vehicle1.licenseNo, vehicle1);
        vehicleMap.put(vehicle2.licenseNo, vehicle2);
        vehicleMap.put(vehicle3.licenseNo, vehicle3);

        User user = new User(1, "Aditya Nehria");
        userMap.put(user.userId, user);
    }
    private Repository() {
        this.storeMap = new HashMap<>();
        this.userMap = new HashMap<>();
        this.vehicleMap = new HashMap<>();
        this.reservationMap = new HashMap<>();
        initData();
    }


    public List<Vehicle> getAllVehiclesByStoreId (int storeId) {
        return vehicleMap.values().stream().filter(vehicle -> vehicle.storeId == storeId).toList();
    }

    public List<Vehicle> getAllVehiclesByStoreIdAndType (int storeId, VehicleType vehicleType) {
        return vehicleMap.values().stream().filter(vehicle -> vehicle.storeId == storeId && vehicle.vehicleType == vehicleType).toList();
    }

    public List<Reservation> getAllReservationsByStoreId(int storeId) {
        return reservationMap.values().stream().filter(reservation -> reservation.storeId == storeId).toList();
    }

    public List<Reservation> getAllReservationsByUserName(String userName) {
        return reservationMap.values().stream().filter(reservation -> reservation.userName.equals(userName)).toList();
    }

    public List<String> getBookedVehicleLicenseNo(int storeId, LocalDate startDate, LocalDate endDate) {
        return reservationMap.values().stream()
                .filter(reservation -> reservation.storeId == storeId
                        && datesOverlap(reservation.reservationFrom, reservation.reservationTo, startDate, endDate)
                        && reservation.reservationStatus  != ReservationStatus.CANCELLED)
                .map(reservation ->reservation.vehicleLicenseNo)
                .toList();
    }

    boolean datesOverlap(LocalDate resFrom, LocalDate resTo, LocalDate startDate, LocalDate endDate) {
        return !(resTo.isBefore(startDate) || resFrom.isAfter(endDate));
    }

    public void addReservation(Reservation reservation) {
        String reservationId = reservation.reservationId;
        if(reservationMap.containsKey(reservationId))
            throw new ValidationException("Reservation already exists with reservation id :: " + reservationId);
        reservationMap.put(reservationId, reservation);
    }

    public void updateReservationStatus(String reservationId, ReservationStatus reservationStatus) {
        if(!reservationMap.containsKey(reservationId))
            throw new ValidationException("Reservation does not exist with reservation id :: " + reservationId);
        var reservation = reservationMap.get(reservationId);
        reservation.reservationStatus = reservationStatus;
        reservationMap.put(reservationId, reservation);
    }
}
