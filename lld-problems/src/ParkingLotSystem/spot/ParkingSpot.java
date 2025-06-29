package ParkingLotSystem.spot;

import ParkingLotSystem.vehicle.Vehicle;


import java.util.Objects;

public class ParkingSpot extends Spot{
    private Vehicle vehicle;
    private ParkingSpotType parkingSpotType;

    public ParkingSpot(int rowNumber, int colNumber, ParkingSpotType parkingSpotType) {
        super(SpotType.PARKING_SPOT, rowNumber, colNumber);
        vehicle = null;
        this.parkingSpotType = parkingSpotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void setParkingSpotType(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }

    public boolean isAvailable() {
        if(Objects.nonNull(vehicle)) return false;
        else return true;
    }

    synchronized void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    synchronized void unParkVehicle() {
        vehicle = null;
    }
}
