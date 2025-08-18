package CarRentalSystem.model.vehicle;

import CarRentalSystem.enums.VehicleType;

public class HatchBack extends Vehicle{

    public HatchBack(VehicleType vehicleType, int vehicleId, String licenseNo, int storeId) {
        super(vehicleType, vehicleId, licenseNo, storeId);
    }
}
