package CarRentalSystem.model;

import CarRentalSystem.enums.ReservationStatus;

import java.time.LocalDate;

public class Reservation {
    public String reservationId;
    public ReservationStatus reservationStatus;
    public String vehicleLicenseNo;
    public String userName;
    public LocalDate reservationDate;
    public LocalDate reservationFrom;
    public LocalDate reservationTo;
    public int storeId;
    public double cost;

    public Reservation(String reservationId,ReservationStatus reservationStatus, String vehicleLicenseNo, String userName, LocalDate reservationDate, LocalDate reservationTo, LocalDate reservationFrom, int storeId) {
        this.reservationId = reservationId;
        this.reservationStatus = reservationStatus;
        this.vehicleLicenseNo = vehicleLicenseNo;
        this.userName = userName;
        this.reservationDate = reservationDate;
        this.reservationTo = reservationTo;
        this.reservationFrom = reservationFrom;
        this.storeId = storeId;
        this.cost = 0.0;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", reservationStatus=" + reservationStatus +
                ", vehicleLicenseNo='" + vehicleLicenseNo + '\'' +
                ", userName='" + userName + '\'' +
                ", reservationDate=" + reservationDate +
                ", reservationFrom=" + reservationFrom +
                ", reservationTo=" + reservationTo +
                ", storeId=" + storeId +
                ", cost=" + cost +
                '}';
    }
}
