package CarRentalSystem;

import CarRentalSystem.model.Reservation;
import CarRentalSystem.model.vehicle.Vehicle;
import CarRentalSystem.repository.Repository;
import CarRentalSystem.service.CustomerService;

import java.time.LocalDate;
import java.util.List;

public class CarRentalSystemTestDrive {
    public static void main(String args[]) {
        Repository repository = Repository.getInstance();
        CustomerService customerService = new CustomerService(repository);

        System.out.println("Getting all vehicles by store id");
        List<Vehicle> allVehicles = customerService.viewAllVehiclesByStoreId(1);
        allVehicles.forEach(System.out::println);
        System.out.println();


        System.out.println("Viewing available vehicles for date 2025-08-18 -- 2025-08-20");
        List<Vehicle> availableVehicles1 = customerService.viewAvailableVehicleForGivenDate(1, LocalDate.of(2025,8,18), LocalDate.of(2025,8,20));
        availableVehicles1.forEach(System.out::println);
        System.out.println();

        //reserve all vehicles
        System.out.println("Making reservation for Vehicle HP39D1123");
        LocalDate reservation1StartDate = LocalDate.of(2025,8,18);
        LocalDate reservationEndDate = LocalDate.of(2025,8,20);
        Reservation reservation1 = customerService.makeReservation("Aditya Nehria", 1, "HP39D1123",  reservation1StartDate, reservationEndDate);
        System.out.println("Made Reservation :: " + reservation1);

        System.out.println("Making reservation for Vehicle HP39D1124");
        LocalDate reservation2StartDate = LocalDate.of(2025,8,21);
        LocalDate reservation2EndDate = LocalDate.of(2025,8,24);
        Reservation reservation2 = customerService.makeReservation("Aditya Nehria", 1, "HP39D1124",  reservation2StartDate, reservation2EndDate);
        System.out.println("Made Reservation :: " + reservation2);


        System.out.println("Making reservation for Vehicle HP39D1125");
        LocalDate reservation3StartDate = LocalDate.of(2025,8,25);
        LocalDate reservatio3EndDate = LocalDate.of(2025,8,28);
        Reservation reservation3 = customerService.makeReservation("Aditya Nehria", 1, "HP39D1125",  reservation3StartDate, reservatio3EndDate);
        System.out.println("Made Reservation :: " + reservation3);
        System.out.println();

        System.out.println("Viewing available cars for date 2025-08-18 -- 2025-08-28 : Should by empty");
        List<Vehicle> availableVehicles2 = customerService.viewAvailableVehicleForGivenDate(1, LocalDate.of(2025,8,18), LocalDate.of(2025,8,28));
        availableVehicles2.forEach(System.out::println);
        System.out.println();

        System.out.println("Cancelling first reservation");
        customerService.cancelReservation(reservation1.reservationId);
        System.out.println();

        System.out.println("Viewing available vehicles for date 2025-08-18 -- 2025-08-22");
        List<Vehicle> availableVehicles3 = customerService.viewAvailableVehicleForGivenDate(1, LocalDate.of(2025,8,18), LocalDate.of(2025,8,22));
        availableVehicles3.forEach(System.out::println);
        System.out.println();
    }
}
