package CarRentalSystem.service.pricing;

import CarRentalSystem.model.Reservation;

import java.time.temporal.ChronoUnit;

public class DailyPricingStrategy implements PricingStrategy{
    private final double pricePerDay;

    public DailyPricingStrategy(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public double calculatePrice(Reservation reservation) {
        long days = ChronoUnit.DAYS.between(reservation.reservationFrom, reservation.reservationTo);
        if (days == 0) days = 1;
        return days * pricePerDay;
    }
}
