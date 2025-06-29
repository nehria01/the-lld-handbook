package ParkingLotSystem.vehicle;

public abstract class Vehicle {
    private VehicleType vehicleType;
    private String licensePlateNo;

    public Vehicle(VehicleType vehicleType, String licensePlateNo) {
        this.vehicleType = vehicleType;
        this.licensePlateNo = licensePlateNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlateNo() {
        return licensePlateNo;
    }

    public void setLicensePlateNo(String licensePlateNo) {
        this.licensePlateNo = licensePlateNo;
    }
}
