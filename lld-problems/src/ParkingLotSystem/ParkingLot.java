package ParkingLotSystem;

import ParkingLotSystem.invoice.Ticket;
import ParkingLotSystem.observer.ParkingLevelDisplay;
import ParkingLotSystem.observer.ParkingLevelObserver;
import ParkingLotSystem.spot.Entrance;
import ParkingLotSystem.spot.Exit;
import ParkingLotSystem.spot.ParkingSpotType;
import ParkingLotSystem.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static volatile ParkingLot instance = null;
    private final List<ParkingLevel> parkingLevels = new ArrayList<>();
    private final int NUMBER_LEVELS = 3;
    private final int LEVEL_ROWS = 10;
    private final int LEVEL_COLS = 10;
    private final Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();
    private ParkingLevelObserver parkingLevelObserver = new ParkingLevelDisplay();
    private ParkingLot() {}

    public static ParkingLot getInstance() {
        if(instance == null) {
            synchronized (ParkingLot.class) {
                if(instance == null)
                    instance = new ParkingLot();
            }
        }
        return instance;
    }

    public void initialiseParkingLot() {
        for (int levelNum =0;levelNum<NUMBER_LEVELS;levelNum++) {
            ParkingLevel level = new ParkingLevel(levelNum, LEVEL_ROWS, LEVEL_COLS);

            int totalSpots = LEVEL_ROWS*LEVEL_COLS;
            int bikeCount = (int) (0.2*totalSpots);
            int sedanCount = (int) (0.3*totalSpots);
            int suvCount = (int) (0.4*totalSpots);
            int truckCount = totalSpots-(bikeCount+sedanCount+suvCount);

            int spotRow=0, spotCol=0;

            // Place Entrances (top-left and top-right corners)
            level.addEntrance(0, 0);
            level.addEntrance(0, LEVEL_COLS - 1);

            // Place Exits (bottom-left and bottom-right corners)
            level.addExit(LEVEL_ROWS - 1, 0);
            level.addExit(LEVEL_ROWS - 1, LEVEL_COLS - 1);

            // Fill grid with parking spots of different types
            while (bikeCount>0 || sedanCount>0 || suvCount>0 || truckCount>0) {
                if(spotRow>=LEVEL_ROWS) break;
                if ((spotRow==0 && (spotCol==0 || spotCol==LEVEL_COLS-1)) ||
                        (spotRow==LEVEL_ROWS-1 && (spotCol==0 || spotCol==LEVEL_COLS - 1))) {
                    // Skip entrance and exit cells
                    spotCol++;
                    if(spotCol>=LEVEL_COLS) {
                        spotCol = 0;
                        spotRow++;
                    }
                    continue;
                }

                ParkingSpotType typeToAdd = null;

                if (bikeCount > 0) {
                    typeToAdd = ParkingSpotType.MOTORCYCLE;
                    bikeCount--;
                } else if (sedanCount > 0) {
                    typeToAdd = ParkingSpotType.SEDAN;
                    sedanCount--;
                } else if (suvCount > 0) {
                    typeToAdd = ParkingSpotType.SUV;
                    suvCount--;
                } else if (truckCount > 0) {
                    typeToAdd = ParkingSpotType.TRUCK;
                    truckCount--;
                }

                level.addParkingSpot(spotRow, spotCol, typeToAdd);
                spotCol++;
                if (spotCol >= LEVEL_COLS) {
                    spotCol = 0;
                    spotRow++;
                }
            }
            level.registerObserver(parkingLevelObserver);
            parkingLevels.add(level);
        }

        System.out.println("Parking lot initialized with " + NUMBER_LEVELS + " levels.");
    }

    public ParkingLevel getParkingLevel(int levelNumber) {
        return parkingLevels.get(levelNumber);
    }

    public Ticket parkVehicle(int parkingLevel, Entrance entrance, Vehicle vehicle) {
        if (parkingLevel < 0 || parkingLevel >= parkingLevels.size()) {
            System.out.println("Invalid parking level");
            return null;
        }

        ParkingLevel level = parkingLevels.get(parkingLevel);

        // Assign strategy to the entrance if not already set
        Ticket ticket = level.processEntry(vehicle, entrance);
        activeTickets.put(vehicle.getLicensePlateNo(), ticket);
        return ticket;
    }

    public boolean unparkVehicle(int parkingLevel, Exit exit, Ticket ticket, double paymentAmount) {
        if (parkingLevel < 0 || parkingLevel >= parkingLevels.size()) {
            System.out.println("Invalid parking level");
            return false;
        }

        ParkingLevel level = parkingLevels.get(parkingLevel);
        boolean success = level.processExit(ticket, exit, paymentAmount);
        if(success) activeTickets.remove(ticket.getLicensePlateNumber());
        return success;
    }

    public List<ParkingLevel> getParkingLevels() {
        return parkingLevels;
    }

    public Ticket getActiveTicket(String licenseNo) {
        return activeTickets.get(licenseNo);
    }
}
