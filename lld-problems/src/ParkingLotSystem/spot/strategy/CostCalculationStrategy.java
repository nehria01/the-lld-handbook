package ParkingLotSystem.spot.strategy;

import ParkingLotSystem.invoice.Ticket;

public interface CostCalculationStrategy {
    double getCost(Ticket ticket);
}
