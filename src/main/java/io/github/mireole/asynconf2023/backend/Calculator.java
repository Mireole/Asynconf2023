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

    public void updateVehicleType(VehicleTypeEntry vehicleType) {
        if (vehicleType == null) {
            vehicleTypeScore = 0;
        } else {
            vehicleTypeScore = vehicleType.ecoScore();
        }
        updateBaseLoanRate();
    }

    public void updateEnergy(FuelTypeEntry fuelType) {
        if (fuelType == null) {
            energyScore = 0;
        } else {
            energyScore = fuelType.getEcoScore();
        }
        updateBaseLoanRate();
    }

    public void updateBuildYear(YearIntervalEntry yearIntervalEntry) {
        if (yearIntervalEntry == null) {
            buildYearScore = 0;
        } else {
            buildYearScore = yearIntervalEntry.getEcoScore();
        }
        updateBaseLoanRate();
    }

    public void updateKilometers(IntervalEntry entry) {
        if (entry == null) {
            kilometersScore = 0;
        } else {
            kilometersScore = entry.getEcoScore();
        }
        updateBaseLoanRate();
    }

    /**
     * Returns the largest smaller integer if the decimal par is <= 5, otherwise the smallest larger integer.
     */
    private int customRounding(float f) {
        int i = (int) f;
        if (f - i <= 0.5f) {
            return i;
        } else {
            return i + 1;
        }
    }

    private void updateBaseLoanRate() {
        totalScore = customRounding(vehicleTypeScore + energyScore + buildYearScore + kilometersScore);
        LoanRateEntry entry = GUIUtils.getSpinnerEntry((int) totalScore, config.getLoanRates());
        if (entry == null) {
            baseLoanRate = 0;
        } else {
            baseLoanRate = entry.getEcoScore();
        }
        updateLoanRate();
    }

    public void updatePassengerBonus(int i) {
        if (i > config.getPassengers().size() - 1) {
            passengerBonusMalus = 0;
        } else {
            passengerBonusMalus = config.getPassengers().get(i);
        }
        updateLoanRate();
    }

    private void updateLoanRate() {
        loanRate = baseLoanRate + passengerBonusMalus;
    }

    /**
     * Returns the loan rate rounded to two decimal places.
     */
    public float getLoanRate() {
        return (float) Math.round(loanRate * 100) / 100;
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
