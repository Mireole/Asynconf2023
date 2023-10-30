package io.github.mireole.asynconf2023.backend.data;

import java.util.List;

public class Config {
    private List<VehicleTypeEntry> vehicleTypes;
    private List<FuelTypeEntry> fuelTypes;
    private List<IntervalEntry> kilometersPerYear;
    private List<IntervalEntry> vehicleAges;
    private List<LoanRateEntry> loanRates;
    private List<Float> passengers;

    public Config(List<VehicleTypeEntry> vehicleTypes, List<FuelTypeEntry> fuelTypes, List<IntervalEntry> kilometersPerYear, List<IntervalEntry> vehicleAges, List<LoanRateEntry> loanRates, List<Float> passengers) {
        this.vehicleTypes = vehicleTypes;
        this.fuelTypes = fuelTypes;
        this.kilometersPerYear = kilometersPerYear;
        this.vehicleAges = vehicleAges;
        this.loanRates = loanRates;
        this.passengers = passengers;
    }

    public Config() {
    }

}
