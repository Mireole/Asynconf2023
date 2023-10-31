package io.github.mireole.asynconf2023.backend;

import io.github.mireole.asynconf2023.backend.data.*;
import io.github.mireole.asynconf2023.gui.Themes;

import java.util.List;

public class Config {
    protected List<VehicleTypeEntry> vehicleTypes;
    protected List<FuelTypeEntry> fuelTypes;
    protected List<IntervalEntry> kilometersPerYear;
    protected List<YearIntervalEntry> vehicleAges;
    protected List<LoanRateEntry> loanRates;
    protected List<Float> passengers;
    protected Themes theme;
    public Config(List<VehicleTypeEntry> vehicleTypes, List<FuelTypeEntry> fuelTypes, List<IntervalEntry> kilometersPerYear, List<YearIntervalEntry> vehicleAges, List<LoanRateEntry> loanRates, List<Float> passengers, Themes theme) {
        this.vehicleTypes = vehicleTypes;
        this.fuelTypes = fuelTypes;
        this.kilometersPerYear = kilometersPerYear;
        this.vehicleAges = vehicleAges;
        this.loanRates = loanRates;
        this.passengers = passengers;
        this.theme = theme;
    }

    public Themes getTheme() {
        return theme;
    }

    public void setTheme(Themes selectedItem) {
        this.theme = selectedItem;
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

    public void save() {
        ConfigReader.writeConfig(this);
    }
}
