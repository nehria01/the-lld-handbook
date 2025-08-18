package CarRentalSystem.service;

import CarRentalSystem.enums.ReservationStatus;
import CarRentalSystem.exception.ValidationException;
import CarRentalSystem.model.Reservation;
import CarRentalSystem.model.vehicle.Vehicle;
import CarRentalSystem.repository.Repository;
import CarRentalSystem.service.pricing.DailyPricingStrategy;
import CarRentalSystem.service.pricing.PricingStrategy;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class CustomerService {
    private Repository repository;
    private PricingStrategy pricingStrategy;

    public CustomerService(Repository repository) {
        this.repository = repository;
        this.pricingStrategy = new DailyPricingStrategy(100);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public List<Vehicle> viewAllVehiclesByStoreId(int storeId) {
        List<Vehicle> vehicles = repository.getAllVehiclesByStoreId(storeId);
        return vehicles;
    }

    public List<Vehicle> viewAvailableVehicleForGivenDate(int storeId, LocalDate startDate, LocalDate endDate) {
        List<Vehicle> vehicles = repository.getAllVehiclesByStoreId(storeId);
        List<String> bookedVehicles = repository.getBookedVehicleLicenseNo(storeId, startDate, endDate);
        return vehicles.stream().filter(vehicle -> !bookedVehicles.contains(vehicle.licenseNo)).toList();
    }

    public Reservation makeReservation(String userName, int storeId, String vehicleLicenseNo, LocalDate startDate, LocalDate endDate) {
        //validation
        List<String> bookedVehciles = repository.getBookedVehicleLicenseNo(storeId, startDate, endDate);
        if(bookedVehciles.contains(vehicleLicenseNo))
            throw new ValidationException("Vehicle already booked");
        Random random = new Random();
        int number = 100 + random.nextInt(900);
        Reservation  reservation = new Reservation("RES_"+number, ReservationStatus.RESERVED, vehicleLicenseNo, userName, startDate, endDate, startDate, storeId);
        reservation.cost = pricingStrategy.calculatePrice(reservation);
        repository.addReservation(reservation);
        return reservation;
    }

    public void makePayment(String reservationId, double bookingAmount) {
        //process payment
        repository.updateReservationStatus(reservationId, ReservationStatus.BOOKED);
    }

    public void cancelReservation(String reservationId) {
        repository.updateReservationStatus(reservationId, ReservationStatus.CANCELLED);
    }

    public List<Reservation> viewAllReservationsByUserName(String userName) {
        return repository.getAllReservationsByUserName(userName);
    }
}
