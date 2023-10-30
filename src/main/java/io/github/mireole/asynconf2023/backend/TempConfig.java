package io.github.mireole.asynconf2023.backend;

import io.github.mireole.asynconf2023.backend.data.*;

import java.util.List;

public class TempConfig extends Config {
    private final Config c;
    private boolean changed = false;
    public TempConfig(Config config) {
        super(config.getVehicleTypes(), config.getFuelTypes(), config.getKilometersPerYear(), config.getVehicleAges(), config.getLoanRates(), config.getPassengers());
        this.c = config;
    }


    /**
     * Copies all values from this TempConfig to the original Config, effectively saving the changes to the original Config
     */
    public void copy() {
        c.vehicleTypes = this.vehicleTypes;
        c.fuelTypes = this.fuelTypes;
        c.kilometersPerYear = this.kilometersPerYear;
        c.vehicleAges = this.vehicleAges;
        c.loanRates = this.loanRates;
        c.passengers = this.passengers;
    }

    public void setVehicleTypes(List<VehicleTypeEntry> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public void setFuelTypes(List<FuelTypeEntry> fuelTypes) {
        this.fuelTypes = fuelTypes;
    }

    public void setKilometersPerYear(List<IntervalEntry> kilometersPerYear) {
        this.kilometersPerYear = kilometersPerYear;
    }

    public void setVehicleAges(List<YearIntervalEntry> vehicleAges) {
        this.vehicleAges = vehicleAges;
    }

    public void setLoanRates(List<LoanRateEntry> loanRates) {
        this.loanRates = loanRates;
    }

    public void setPassengers(List<Float> passengers) {
        this.passengers = passengers;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
