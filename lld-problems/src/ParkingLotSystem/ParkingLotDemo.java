package ParkingLotSystem;

import ParkingLotSystem.invoice.Ticket;
import ParkingLotSystem.spot.Entrance;
import ParkingLotSystem.spot.Exit;
import ParkingLotSystem.vehicle.Vehicle;
import ParkingLotSystem.vehicle.VehicleFactory;
import ParkingLotSystem.vehicle.VehicleType;

import java.util.*;

public class ParkingLotDemo {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.initialiseParkingLot();

        VehicleType[] vehicleTypes = VehicleType.values();
        Random random = new Random();

        List<Vehicle> parkedVehicles = new ArrayList<>();

        // Simulate multiple levels
        for (ParkingLevel level : parkingLot.getParkingLevels()) {
            int vehiclesToPark = 5;
            List<Entrance> entrances = level.getEntrances();

            System.out.println("\n--- Parking vehicles on Level " + level.getLevelNumber() + " ---");

            for (int i = 0; i < vehiclesToPark; i++) {
                VehicleType type = vehicleTypes[random.nextInt(vehicleTypes.length)];
                String license = "DL" + (10 + random.nextInt(90)) + "XY" + (1000 + random.nextInt(9000));
                Vehicle vehicle = VehicleFactory.getVehicle(license, type);

                Entrance entrance = entrances.get(random.nextInt(entrances.size()));
                Ticket ticket = parkingLot.parkVehicle(level.getLevelNumber(), entrance, vehicle);

                if (ticket != null) {
                    System.out.println("Parked: " + vehicle.getLicensePlateNo() + " (" + type + ")");
                    parkedVehicles.add(vehicle);
                } else {
                    System.out.println("Failed to park: " + vehicle.getLicensePlateNo());
                }
            }
        }

        Thread.sleep(2000);
        System.out.println();

        // Unpark vehicles using license number
        for (Vehicle vehicle : parkedVehicles) {
            String license = vehicle.getLicensePlateNo();
            int levelIndex = new Random().nextInt(parkingLot.getParkingLevels().size());
            ParkingLevel level = parkingLot.getParkingLevels().get(levelIndex);
            List<Exit> exits = level.getExits();
            Exit exit = exits.get(random.nextInt(exits.size()));
            Ticket ticket = parkingLot.getActiveTicket(license);
            boolean success = parkingLot.unparkVehicle(levelIndex, exit, ticket, 500);
            if (success) {
                System.out.println("Unparked: " + license);
            } else {
                System.out.println("Failed to unpark: " + license);
            }
            System.out.println();
        }
    }
}
