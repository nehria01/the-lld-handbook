package ParkingLotSystem.spot;

import ParkingLotSystem.invoice.Ticket;
import ParkingLotSystem.spot.strategy.FindParkingSpotStrategy;
import ParkingLotSystem.spot.strategy.impl.GreedySpotStrategy;
import ParkingLotSystem.vehicle.Vehicle;

public class Entrance extends Spot{
    FindParkingSpotStrategy parkingSpotStrategy;

    public Entrance(int rowNumber, int colNumber) {
        super(SpotType.ENTRANCE, rowNumber, colNumber);
        //by default return first empty compatible spot
        parkingSpotStrategy = new GreedySpotStrategy();
    }

    public Entrance(SpotType spotType, int rowNumber, int colNumber) {
        super(spotType, rowNumber, colNumber);
    }

    public ParkingSpot getParkingSpot(ParkingContext context) {
        return parkingSpotStrategy.getParkingSpot(context);
    }

    public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        return new Ticket(vehicle.getLicensePlateNo(), parkingSpot);
    }

    public boolean parkVehicle(Vehicle vehicle, ParkingSpot parkingSpot) {
        if(parkingSpot.isAvailable()) {
            parkingSpot.parkVehicle(vehicle);
            return true;
        }
        return false;
    }

    public void setParkingSpotStrategy(FindParkingSpotStrategy parkingSpotStrategy) {
        this.parkingSpotStrategy = parkingSpotStrategy;
    }
}
