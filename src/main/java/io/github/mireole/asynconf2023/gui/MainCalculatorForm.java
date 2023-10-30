package io.github.mireole.asynconf2023.gui;

import io.github.mireole.asynconf2023.backend.data.Config;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainCalculatorForm {
    private final Config config;
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

    public MainCalculatorForm(Config config) {
        this.config = config;
    }

    /*
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

    /*
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
        return new SpinnerNumberModel(min.get(), min.get(), max.get(), minStep.get());
    }

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
    }
}
