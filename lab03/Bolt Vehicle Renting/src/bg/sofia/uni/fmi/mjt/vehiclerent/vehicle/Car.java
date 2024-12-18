package bg.sofia.uni.fmi.mjt.vehiclerent.vehicle;

import bg.sofia.uni.fmi.mjt.vehiclerent.exception.InvalidRentingPeriodException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public final class Car extends Vehicle {
    FuelType fuelType;
    int numberOfSeats;
    double pricePerWeek;
    double pricePerDay;
    double pricePerHour;

    public Car(String id, String model, FuelType fuelType, int numberOfSeats, double pricePerWeek,
               double pricePerDay, double pricePerHour) {
        super(id, model);
        this.fuelType = fuelType;
        this.numberOfSeats = numberOfSeats;
        this.pricePerWeek = pricePerWeek;
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
    }

    @Override
    public double calculateRentalPrice(LocalDateTime startOfRent, LocalDateTime endOfRent)
        throws InvalidRentingPeriodException {
        if (endOfRent.isBefore(startOfRent)) {
            throw new InvalidRentingPeriodException("End date must be after start date!");
        }

        final int daysInWeek = 7;
        final int priceOfSeat = 5;

        long days = ChronoUnit.DAYS.between(startOfRent, endOfRent);
        long weeks = days / daysInWeek;
        days = days % daysInWeek;

        double totalPrice = weeks * pricePerWeek + days * pricePerDay;
        totalPrice += fuelType.getDailyFee() * (weeks * daysInWeek + days);
        totalPrice += numberOfSeats * priceOfSeat;

        totalPrice += getDriverFee(this.rentedBy);

        return totalPrice;
    }
}
