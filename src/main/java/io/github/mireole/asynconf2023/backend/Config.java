package io.github.mireole.asynconf2023.backend;

import io.github.mireole.asynconf2023.backend.data.*;

import java.util.List;

public class Config {
    private List<VehicleTypeEntry> vehicleTypes;
    private List<FuelTypeEntry> fuelTypes;
    private List<IntervalEntry> kilometersPerYear;
    private List<YearIntervalEntry> vehicleAges;
    private List<LoanRateEntry> loanRates;
    private List<Float> passengers;

    public Config(List<VehicleTypeEntry> vehicleTypes, List<FuelTypeEntry> fuelTypes, List<IntervalEntry> kilometersPerYear, List<YearIntervalEntry> vehicleAges, List<LoanRateEntry> loanRates, List<Float> passengers) {
        this.vehicleTypes = vehicleTypes;
        this.fuelTypes = fuelTypes;
        this.kilometersPerYear = kilometersPerYear;
        this.vehicleAges = vehicleAges;
        this.loanRates = loanRates;
        this.passengers = passengers;
    }

    public Config() {
    }

    public List<VehicleTypeEntry> getVehicleTypes() {
        return vehicleTypes;
    }

    public List<FuelTypeEntry> getFuelTypes() {
        return fuelTypes;
    }

    public List<IntervalEntry> getKilometersPerYear() {
        return kilometersPerYear;
    }

    public List<YearIntervalEntry> getVehicleAges() {
        return vehicleAges;
    }

    public List<LoanRateEntry> getLoanRates() {
        return loanRates;
    }

    public List<Float> getPassengers() {
        return passengers;
    }
}
