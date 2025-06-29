package ParkingLotSystem.observer;

import ParkingLotSystem.ParkingLevel;
import ParkingLotSystem.spot.ParkingSpotType;

import java.util.Map;

public class ParkingLevelDisplay implements ParkingLevelObserver {
    @Override
    public void update(ParkingLevel level) {
        System.out.println("\n=== Display Update for Level " + level.getLevelNumber() + " ===");

        Map<ParkingSpotType, Integer> totalByType = level.getTotalSpotsByType();
        Map<ParkingSpotType, Integer> availableByType = level.getAvailableSpotsByType();

        System.out.printf("%-15s %-10s %-10s %-10s%n", "Spot Type", "Total", "Occupied", "Available");
        System.out.println("--------------------------------------------------");

        for (ParkingSpotType type : ParkingSpotType.values()) {
            int total = totalByType.getOrDefault(type, 0);
            int available = availableByType.getOrDefault(type, 0);
            int occupied = total - available;

            System.out.printf("%-15s %-10d %-10d %-10d%n", type, total, occupied, available);
        }

        System.out.println("==================================================\n");
    }
}
