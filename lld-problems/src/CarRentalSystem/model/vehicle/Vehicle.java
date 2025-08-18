package CarRentalSystem.model.vehicle;

import CarRentalSystem.enums.VehicleType;

public abstract class Vehicle {
    public VehicleType vehicleType;
    public int vehicleId;
    public String licenseNo;
    public int storeId;

    public Vehicle(VehicleType vehicleType, int vehicleId, String licenseNo, int storeId) {
        this.vehicleType = vehicleType;
        this.vehicleId = vehicleId;
        this.licenseNo = licenseNo;
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleType=" + vehicleType +
                ", vehicleId=" + vehicleId +
                ", licenseNo='" + licenseNo + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
