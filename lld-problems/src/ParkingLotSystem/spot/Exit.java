package ParkingLotSystem.spot;

import ParkingLotSystem.invoice.Ticket;
import ParkingLotSystem.spot.strategy.CostCalculationStrategy;
import ParkingLotSystem.spot.strategy.impl.PerHourCostStrategy;

public class Exit extends Spot {
    CostCalculationStrategy costCalculationStrategy;

    public Exit(int rowNumber, int colNumber) {
        super(SpotType.EXIT, rowNumber, colNumber);
        costCalculationStrategy = new PerHourCostStrategy();
    }

    public boolean unParkVehicle(ParkingSpot parkingSpot) {
        parkingSpot.unParkVehicle();
        return true;
    }

    public boolean processExit(ParkingSpot spot, Ticket ticket, double amount) {
        double cost = getCost(ticket);
        if (amount >= cost) {
            collectPayment(amount, ticket);
            return unParkVehicle(spot);
        } else {
            System.out.println("Insufficient payment.");
            return false;
        }
    }

    public double getCost(Ticket ticket) {
        return costCalculationStrategy.getCost(ticket);
    }

    private boolean collectPayment(double payment, Ticket ticketId) {
        System.out.println("Payment collected against ticketId :: " + ticketId.getId());
        return true;
    }

    public void setCostCalculationStrategy(CostCalculationStrategy costCalculationStrategy) {
        this.costCalculationStrategy = costCalculationStrategy;
    }
}
