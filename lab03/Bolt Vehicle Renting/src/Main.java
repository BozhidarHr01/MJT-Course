import bg.sofia.uni.fmi.mjt.vehiclerent.RentalService;
import bg.sofia.uni.fmi.mjt.vehiclerent.driver.AgeGroup;
import bg.sofia.uni.fmi.mjt.vehiclerent.driver.Driver;
import bg.sofia.uni.fmi.mjt.vehiclerent.exception.VehicleAlreadyRentedException;
import bg.sofia.uni.fmi.mjt.vehiclerent.exception.VehicleNotRentedException;
import bg.sofia.uni.fmi.mjt.vehiclerent.exception.InvalidRentingPeriodException;
import bg.sofia.uni.fmi.mjt.vehiclerent.vehicle.Car;
import bg.sofia.uni.fmi.mjt.vehiclerent.vehicle.FuelType;
import bg.sofia.uni.fmi.mjt.vehiclerent.vehicle.Vehicle;


public static void main(String[] args)
    throws VehicleAlreadyRentedException, VehicleNotRentedException, InvalidRentingPeriodException {
    RentalService rentalService = new RentalService();
    LocalDateTime rentStart = LocalDateTime.of(2024, 10, 10, 0, 0, 0);
    Driver experiencedDriver = new Driver(AgeGroup.EXPERIENCED);

    try {
        Vehicle electricCar = new Car("1", "Tesla Model 3", FuelType.ELECTRICITY, 4, 1000, 150, 10);
        rentalService.rentVehicle(experiencedDriver, electricCar, rentStart);
        double priceToPay = rentalService.returnVehicle(electricCar, rentStart.plusDays(5)); // 770.0

        System.out.println(priceToPay);
        Vehicle dieselCar = new Car("2", "Toyota Auris", FuelType.DIESEL, 4, 500, 80, 5);
        rentalService.rentVehicle(experiencedDriver, dieselCar, rentStart);
        priceToPay = rentalService.returnVehicle(dieselCar, rentStart.plusDays(5)); // 80*5 + 3*5 + 4*5 = 435.0
        System.out.println(priceToPay);
    } catch (VehicleAlreadyRentedException | InvalidRentingPeriodException e) {
        System.err.println("An error occurred: " + e.getMessage());
    }
}