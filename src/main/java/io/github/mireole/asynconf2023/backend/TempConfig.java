package io.github.mireole.asynconf2023.backend;

import java.util.ArrayList;

public class TempConfig extends Config {
    private final Config c;

    public TempConfig(Config config) {
        super(
                new ArrayList<>(config.getVehicleTypes()),
                new ArrayList<>(config.getFuelTypes()),
                new ArrayList<>(config.getKilometersPerYear()),
                new ArrayList<>(config.getVehicleAges()),
                new ArrayList<>(config.getLoanRates()),
                new ArrayList<>(config.getPassengers()),
                config.theme
        );
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
        c.theme = this.theme;
    }


}
