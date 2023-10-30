package io.github.mireole.asynconf2023.backend;

import io.github.mireole.asynconf2023.backend.data.*;
import io.github.mireole.asynconf2023.gui.GUIUtils;

/*
    * This class is responsible for calculating the different scores and the loan rate
    * Everything is cached to avoid recalculating everything every time, even though it's not really necessary
 */
public class Calculator {
    private final Config config;
    // Cached values
    private float vehicleTypeScore;
    private float energyScore;
    private float buildYearScore;
    private float kilometersScore;
    private float totalScore;
    // Loan rate without bonus / malus
    private float baseLoanRate;
    private float passengerBonusMalus;
    private float loanRate;


    public Calculator(Config config) {
        this.config = config;
    }

    public float updateVehicleType(VehicleTypeEntry vehicleType) {
        if (vehicleType == null) {
            vehicleTypeScore = 0;
        } else {
            vehicleTypeScore = vehicleType.getEcoScore();
        }
        updateBaseLoanRate();
        return vehicleTypeScore;
    }

    public float updateEnergy(FuelTypeEntry fuelType) {
        if (fuelType == null) {
            energyScore = 0;
        } else {
            energyScore = fuelType.getEcoScore();
        }
        updateBaseLoanRate();
        return energyScore;
    }

    public float updateBuildYear(YearIntervalEntry yearIntervalEntry) {
        if (yearIntervalEntry == null) {
            buildYearScore = 0;
        } else {
            buildYearScore = yearIntervalEntry.getEcoScore();
        }
        updateBaseLoanRate();
        return buildYearScore;
    }

    public float updateKilometers(IntervalEntry entry) {
        if (entry == null) {
            kilometersScore = 0;
        } else {
            kilometersScore = entry.getEcoScore();
        }
        updateBaseLoanRate();
        return kilometersScore;
    }

    private void updateBaseLoanRate() {
        totalScore = (int) Math.floor(vehicleTypeScore + energyScore + buildYearScore + kilometersScore);
        LoanRateEntry entry = GUIUtils.getSpinnerEntry((int) totalScore, config.getLoanRates());
        if (entry == null) {
            baseLoanRate = 0;
        } else {
            baseLoanRate = entry.getValue();
        }
        updateLoanRate();
    }

    public float updatePassengerBonus(int i) {
        if (i > config.getPassengers().size() - 1) {
            passengerBonusMalus = 0;
        } else {
            passengerBonusMalus = config.getPassengers().get(i);
        }
        updateLoanRate();
        return passengerBonusMalus;
    }

    private void updateLoanRate() {
        loanRate = baseLoanRate + passengerBonusMalus;
    }

    public float getLoanRate() {
        return loanRate;
    }

    public float getVehicleTypeScore() {
        return vehicleTypeScore;
    }

    public float getEnergyScore() {
        return energyScore;
    }

    public float getBuildYearScore() {
        return buildYearScore;
    }

    public float getKilometersScore() {
        return kilometersScore;
    }

    /**
     * Returns the bonus / malus for the passenger count rounded to two decimal places.
     */
    public float getPassengerBonusMalus() {
        return (float) Math.round(passengerBonusMalus * 100) / 100;
    }

    public float getTotalScore() {
        return totalScore;
    }
}
