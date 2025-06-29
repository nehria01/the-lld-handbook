package ParkingLotSystem.invoice;

import ParkingLotSystem.spot.ParkingSpot;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    String id;
    private String licensePlateNumber;
    private LocalDateTime entryTime;
    private ParkingSpot parkingSpot;

    public Ticket(String licensePlateNumber, ParkingSpot parkingSpot) {
        id = UUID.randomUUID().toString();
        this.licensePlateNumber = licensePlateNumber;
        this.entryTime = LocalDateTime.now();
        this.parkingSpot = parkingSpot;
    }

    public String getId() {
        return id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }


    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
