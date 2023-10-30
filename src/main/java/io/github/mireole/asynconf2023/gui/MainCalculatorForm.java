package io.github.mireole.asynconf2023.gui;

import io.github.mireole.asynconf2023.backend.Calculator;
import io.github.mireole.asynconf2023.backend.Config;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainCalculatorForm {
    private final Config config;
    private final Calculator calculator;
    private JComboBox vehicleType;
    private JComboBox energy;
    private JComboBox householdCount;
    private JSpinner buildYear;
    private JSpinner kilometersPerYear;
    JPanel contentPane;
    private JLabel vehicleTypeScore;
    private JLabel energyScore;
    private JLabel buildYearScore;
    private JLabel kilometersScore;
    private JLabel householdBonus;
    private JLabel totalScore;
    private JLabel loanRate;

    public MainCalculatorForm(Config config, Calculator calculator) {
        this.calculator = calculator;
        this.config = config;
        initComponents();
    }

    /**
     * Convert a list of ComboBoxEntry to a String array with the visual name of each entry
     */
    private String[] comboBoxEntriesToStringArray(List<? extends ComboBoxEntry> entries) {
        if (entries.isEmpty()) {
            return new String[0];
        }
        String[] result = new String[entries.size()];
        for (int i = 0; i < entries.size(); i++) {
            result[i] = entries.get(i).getVisualName();
        }
        return result;
    }

    /**
     * Create a SpinnerNumberModel using a list of SpinnerEntry
     */
    private SpinnerNumberModel spinnerEntriesToNumberModel(List<? extends SpinnerEntry> entries) {
        // min, max and min step of ALL entries
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger max = new AtomicInteger(0);
        AtomicInteger minStep = new AtomicInteger(Integer.MAX_VALUE);
        if (entries.isEmpty()) {
            return new SpinnerNumberModel();
        }
        entries.forEach(entry -> {
            if (entry.getMinValue() < min.get()) {
                min.set(entry.getMinValue());
            }
            // -1 means no minimum
            else if (entry.getMinValue() == -1) {
                min.set(0);
            }
            if (entry.getMaxValue() > max.get()) {
                max.set(entry.getMaxValue());
            }
            // -1 means no maximum
            else if (entry.getMaxValue() == -1) {
                max.set(Integer.MAX_VALUE);
            }
            if (entry.getStep() < minStep.get()) {
                minStep.set(entry.getStep());
            }
        });
        // max - 1 because the max value is inclusive, we'll get a bug at the max value otherwise
        return new SpinnerNumberModel(min.get(), min.get(), max.get() - 1, minStep.get());
    }

    private float getMinBonus() {
        float minBonus = Float.MAX_VALUE;
        for (int i = 0; i < config.getPassengers().size(); i++) {
            float bonus = config.getPassengers().get(i);
            if (bonus < minBonus) {
                minBonus = bonus;
            }
        }
        return minBonus;
    }

    private float getMaxBonus() {
        float maxBonus = -Float.MAX_VALUE;
        for (int i = 0; i < config.getPassengers().size(); i++) {
            float bonus = config.getPassengers().get(i);
            if (bonus > maxBonus) {
                maxBonus = bonus;
            }
        }
        return maxBonus;
    }

    private float getMinLoanRate() {
        float minLoanRate = Float.MAX_VALUE;
        for (int i = 0; i < config.getLoanRates().size(); i++) {
            float loanRate = config.getLoanRates().get(i).getValue();
            if (loanRate < minLoanRate) {
                minLoanRate = loanRate;
            }
        }
        return minLoanRate;
    }

    private float getMaxLoanRate() {
        float maxLoanRate = -Float.MAX_VALUE;
        for (int i = 0; i < config.getLoanRates().size(); i++) {
            float loanRate = config.getLoanRates().get(i).getValue();
            if (loanRate > maxLoanRate) {
                maxLoanRate = loanRate;
            }
        }
        return maxLoanRate;
    }

    /**
     * Creates the user interface components for the application.
     * Populates the vehicleType, energy, buildYear, kilometersPerYear, and householdCount fields.
     */
    private void createUIComponents() {
        vehicleType = new JComboBox<>(comboBoxEntriesToStringArray(config.getVehicleTypes()));
        energy = new JComboBox<>(comboBoxEntriesToStringArray(config.getFuelTypes()));
        buildYear = new JSpinner(spinnerEntriesToNumberModel(config.getVehicleAges()));
        kilometersPerYear = new JSpinner(spinnerEntriesToNumberModel(config.getKilometersPerYear()));

        // The household count is a special case because it is just a list of float for the bonus / penalties, indexes are what we are interested in
        String[] householdCountEntries = new String[config.getPassengers().size()];
        for (int i = 0; i < config.getPassengers().size(); i++) {
            householdCountEntries[i] = String.valueOf(i + 1);
        }
        householdCount = new JComboBox<>(householdCountEntries);

        vehicleTypeScore = new JLabel();
        energyScore = new JLabel();
        buildYearScore = new JLabel();
        kilometersScore = new JLabel();
        householdBonus = new JLabel();
        totalScore = new JLabel();
        loanRate = new JLabel();

        registerListeners();
    }


    /**
     * Initializes the user interface components.
     * Should be called after GUI Designer's initialization code.
     */
    private void initComponents() {
        calculator.updateVehicleType(GUIUtils.getComboBoxEntry((String) vehicleType.getSelectedItem(), config.getVehicleTypes()));
        calculator.updateEnergy(GUIUtils.getComboBoxEntry((String) energy.getSelectedItem(), config.getFuelTypes()));
        calculator.updateBuildYear(GUIUtils.getSpinnerEntry((Integer) buildYear.getValue(), config.getVehicleAges()));
        calculator.updateKilometers(GUIUtils.getSpinnerEntry((Integer) kilometersPerYear.getValue(), config.getKilometersPerYear()));
        calculator.updatePassengerBonus(householdCount.getSelectedIndex());
        updateScores();
    }

    /**
     * Registers the listeners for the user interface components.
     */
    private void registerListeners() {
        vehicleType.addActionListener(e -> {
            calculator.updateVehicleType(GUIUtils.getComboBoxEntry((String) vehicleType.getSelectedItem(), config.getVehicleTypes()));
            updateScores();
        });
        energy.addActionListener(e -> {
            calculator.updateEnergy(GUIUtils.getComboBoxEntry((String) energy.getSelectedItem(), config.getFuelTypes()));
            updateScores();
        });
        buildYear.addChangeListener(e -> {
            calculator.updateBuildYear(GUIUtils.getSpinnerEntry((Integer) buildYear.getValue(), config.getVehicleAges()));
            updateScores();
        });
        kilometersPerYear.addChangeListener(e -> {
            calculator.updateKilometers(GUIUtils.getSpinnerEntry((Integer) kilometersPerYear.getValue(), config.getKilometersPerYear()));
            updateScores();
        });
        householdCount.addActionListener(e -> {
            calculator.updatePassengerBonus(householdCount.getSelectedIndex());
            updateScores();
        });
    }

    /**
     * Updates the score labels and the loan rate label.
     */
    private void updateScores() {
        vehicleTypeScore.setText(calculator.getVehicleTypeScore() + "/10");
        energyScore.setText(calculator.getEnergyScore() + "/10");
        buildYearScore.setText(calculator.getBuildYearScore() + "/10");
        kilometersScore.setText(calculator.getKilometersScore() + "/10");

        householdBonus.setText(calculator.getPassengerBonusMalus() + "%");

        totalScore.setText((int) Math.floor(calculator.getTotalScore()) + "/40");
        loanRate.setText(calculator.getLoanRate() + "%");

        updateColors();
    }

    private void updateColors() {
        vehicleTypeScore.setForeground(new Color(GUIUtils.interpolateColor(
                Color.red.getRGB(), Color.green.getRGB(), calculator.getVehicleTypeScore(), 10
        )));
        energyScore.setForeground(new Color(GUIUtils.interpolateColor(
                Color.red.getRGB(), Color.green.getRGB(), calculator.getEnergyScore(), 10
        )));
        buildYearScore.setForeground(new Color(GUIUtils.interpolateColor(
                Color.red.getRGB(), Color.green.getRGB(), calculator.getBuildYearScore(), 10
        )));
        kilometersScore.setForeground(new Color(GUIUtils.interpolateColor(
                Color.red.getRGB(), Color.green.getRGB(), calculator.getKilometersScore(), 10
        )));
        float minBonus = getMinBonus();
        float maxBonus = getMaxBonus();
        // Weird line because we have to invert the direction of the color interpolation and account for negative values of minBonus
        householdBonus.setForeground(new Color(GUIUtils.interpolateColor(
                Color.green.getRGB(), Color.red.getRGB(), calculator.getPassengerBonusMalus() - minBonus, maxBonus - minBonus
        )));

        totalScore.setForeground(new Color(GUIUtils.interpolateColor(
                Color.red.getRGB(), Color.green.getRGB(), calculator.getTotalScore(), 40
        )));

        float minLoanRate = getMinLoanRate() + minBonus;
        float maxLoanRate = getMaxLoanRate() + maxBonus;
        // Same as above
        loanRate.setForeground(new Color(GUIUtils.interpolateColor(
                Color.green.getRGB(), Color.red.getRGB(), calculator.getLoanRate() - minLoanRate, maxLoanRate - minLoanRate
        )));


    }

}
