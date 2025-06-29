package ParkingLotSystem.vehicle;

public class VehicleFactory {
    public static Vehicle getVehicle(String licenseNo, VehicleType vehicleType) {
        Vehicle vehicle = null;
        switch(vehicleType) {
            case MOTORCYCLE :
                vehicle = new MotorCycle(licenseNo);
                break;
            case SEDAN :
                vehicle = new Sedan(licenseNo);
                break;
            case SUV :
                vehicle = new SUV(licenseNo);
                break;
            case TRUCK :
                vehicle = new Truck(licenseNo);
                break;
            default:
                System.out.println("Invalid Vehicle Type");
        }
        return vehicle;
    }
}
