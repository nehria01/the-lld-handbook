package ParkingLotSystem;

import ParkingLotSystem.invoice.Ticket;
import ParkingLotSystem.observer.ParkingLevelObserver;
import ParkingLotSystem.observer.Subject;
import ParkingLotSystem.spot.*;
import ParkingLotSystem.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ParkingLevel implements Subject {
    int levelNumber;
    List<List<Spot>> levelSpots;
    private List<Entrance> entrances = new ArrayList<>();
    private List<Exit> exits = new ArrayList<>();
    private List<ParkingLevelObserver> parkingLevelObservers = new ArrayList<>();

    public ParkingLevel(int levelNumber, int rows, int cols) {
        this.levelNumber = levelNumber;
        this.levelSpots = new ArrayList<>();

        for (int i=0;i<rows;i++) {
            List<Spot> row = new ArrayList<>();
            for (int j =0;j<cols;j++) {
                row.add(null);
            }
            levelSpots.add(row);
        }
    }


    public void addEntrance(int row, int col) {
        Entrance entrance = new Entrance(row, col);
        levelSpots.get(row).set(col, entrance);
        entrances.add(entrance);
    }

    public void addExit(int row, int col) {
        Exit exit = new Exit(row, col);
        levelSpots.get(row).set(col, exit);
        exits.add(exit);
    }

    public List<Entrance> getEntrances() {
        return entrances;
    }

    public List<Exit> getExits() {
        return exits;
    }

    public void addParkingSpot(int row, int col, ParkingSpotType type) {
        try {
            levelSpots.get(row).set(col, new ParkingSpot(row, col, type));
        } catch(Exception ex) {
            System.out.println("Exception!");
        }
    }

    public Spot getLevelSpot(int row, int col) {
        return levelSpots.get(row).get(col);
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public Ticket processEntry(Vehicle vehicle, Entrance entrance) {
        //1.Create ParkingContext
        ParkingContext context = new ParkingContext(entrance, levelSpots, vehicle, levelNumber);

        //2.Get available spot from strategy
        ParkingSpot spot = entrance.getParkingSpot(context);
        if (spot == null) {
            System.out.println("No available spot for vehicle " + vehicle.getLicensePlateNo());
            return null;
        }

        //3.Park the vehicle
        if (!entrance.parkVehicle(vehicle, spot)) {
            System.out.println("Parking failed for vehicle " + vehicle.getLicensePlateNo());
            return null;
        }

        //4.Generate ticket
        Ticket ticket = entrance.generateTicket(vehicle, spot);
        System.out.println("Vehicle parked at Level " + levelNumber + " | Ticket ID: " + ticket.getId());
        notifyObservers();
        return ticket;
    }

    public boolean processExit(Ticket ticket, Exit exit, double paymentAmount) {
        ParkingSpot spot = ticket.getParkingSpot();
        if (spot == null) {
            System.out.println("Invalid parking spot for ticket: " + ticket.getId());
            return false;
        }

        boolean exitProcessed = exit.processExit(spot, ticket, paymentAmount);
        if(!exitProcessed) return false;
        System.out.println("Vehicle with ticket " + ticket.getId() + " exited. Payment: ₹" + paymentAmount);
        notifyObservers();
        return true;
    }

    @Override
    public void registerObserver(ParkingLevelObserver parkingLevelObserver) {
        parkingLevelObservers.add(parkingLevelObserver);
    }

    @Override
    public void removeObserver(ParkingLevelObserver parkingLevelObserver) {
        parkingLevelObservers.remove(parkingLevelObserver);
    }

    @Override
    public void notifyObservers() {
        for(var observer : parkingLevelObservers)
            observer.update(this);
    }

    public Map<ParkingSpotType, Integer> getTotalSpotsByType() {
        Map<ParkingSpotType, Integer> m = new EnumMap<>(ParkingSpotType.class);
        for(List<Spot> row: levelSpots) {
            for(Spot spot : row) {
                if(spot instanceof ParkingSpot ps) {
                    ParkingSpotType type = ps.getParkingSpotType();
                    m.put(type, m.getOrDefault(type,0) + 1);
                }
            }
        }
        return m;
    }

    public Map<ParkingSpotType, Integer> getAvailableSpotsByType() {
        Map<ParkingSpotType, Integer> m = new EnumMap<>(ParkingSpotType.class);
        for(List<Spot> row: levelSpots) {
            for(Spot spot : row) {
                if(spot instanceof ParkingSpot ps && ps.isAvailable()) {
                    ParkingSpotType type = ps.getParkingSpotType();
                    m.put(type, m.getOrDefault(type,0) + 1);
                }
            }
        }
        return m;
    }
}
