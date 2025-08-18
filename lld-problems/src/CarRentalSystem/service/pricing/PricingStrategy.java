package CarRentalSystem.service.pricing;

import CarRentalSystem.model.Reservation;

public interface PricingStrategy {
    double calculatePrice(Reservation reservation);
}
