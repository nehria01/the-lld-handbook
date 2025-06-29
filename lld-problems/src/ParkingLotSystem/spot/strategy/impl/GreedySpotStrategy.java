package ParkingLotSystem.spot.strategy.impl;

import ParkingLotSystem.spot.ParkingContext;
import ParkingLotSystem.spot.ParkingSpot;
import ParkingLotSystem.spot.Spot;
import ParkingLotSystem.spot.strategy.FindParkingSpotStrategy;
import ParkingLotSystem.vehicle.Vehicle;

import java.util.List;

public class GreedySpotStrategy implements FindParkingSpotStrategy {

    @Override
    public ParkingSpot getParkingSpot(ParkingContext context) {
        List<List<Spot>> grid = context.getLevelSpots();
        Vehicle vehicle = context.getVehicle();

        for (List<Spot> row : grid) {
            for (Spot spot : row) {
                if (spot instanceof ParkingSpot ps &&
                        ps.isAvailable() &&
                        isCompatible(ps, vehicle)) {
                    return ps;
                }
            }
        }
        return null;
    }

    private boolean isCompatible(ParkingSpot spot, Vehicle vehicle) {
        if (spot.getParkingSpotType().toString().equals(vehicle.getVehicleType().toString()))
            return true;
        else return false;
    }
}

