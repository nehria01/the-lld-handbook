package CarRentalSystem.model.vehicle;

import CarRentalSystem.enums.VehicleType;

public class Sedan extends Vehicle{


    public Sedan(VehicleType vehicleType, int vehicleId, String licenseNo, int storeId) {
        super(vehicleType, vehicleId, licenseNo, storeId);
    }
}
