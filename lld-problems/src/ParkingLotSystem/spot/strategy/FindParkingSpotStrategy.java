package ParkingLotSystem.spot.strategy;

import ParkingLotSystem.spot.ParkingContext;
import ParkingLotSystem.spot.ParkingSpot;

public interface FindParkingSpotStrategy {
        ParkingSpot getParkingSpot(ParkingContext context);
}
