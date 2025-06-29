package ParkingLotSystem.spot.strategy.impl;

import ParkingLotSystem.invoice.Ticket;
import ParkingLotSystem.spot.strategy.CostCalculationStrategy;

import java.time.LocalDateTime;
import java.time.Duration;
public class PerHourCostStrategy implements CostCalculationStrategy {
    private static final double COST_PER_HOUR = 100.00;
    @Override
    public double getCost(Ticket ticket) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();
        Duration duration = Duration.between(entryTime, exitTime);
        long minutes = duration.toMinutes();
        long hours = (minutes + 59) / 60;

        return (hours+5 * COST_PER_HOUR);
    }
}
