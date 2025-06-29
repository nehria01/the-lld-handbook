package ParkingLotSystem.spot;

import ParkingLotSystem.vehicle.Vehicle;

import java.util.List;

public class ParkingContext{
    private final Entrance entrance;
    private final List<List<Spot>> levelSpots;
    private final Vehicle vehicle;
    private final int levelNumber;

    public ParkingContext(Entrance entrance, List<List<Spot>> levelSpots, Vehicle vehicle, int levelNumber) {
        this.entrance = entrance;
        this.levelSpots = levelSpots;
        this.vehicle = vehicle;
        this.levelNumber = levelNumber;
    }

    public Entrance getEntrance() {
        return entrance;
    }

    public List<List<Spot>> getLevelSpots() {
        return levelSpots;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
