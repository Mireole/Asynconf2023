package io.github.mireole.asynconf2023.gui;

import io.github.mireole.asynconf2023.backend.Config;
import io.github.mireole.asynconf2023.backend.TempConfig;
import io.github.mireole.asynconf2023.backend.data.IntervalEntry;
import io.github.mireole.asynconf2023.backend.data.LoanRateEntry;
import io.github.mireole.asynconf2023.backend.data.YearIntervalEntry;
import io.github.mireole.asynconf2023.gui.components.*;

import javax.swing.*;

public class SettingsForm {
    private final Config config;
    private final Window window;
    private final TempConfig tempConfig;
    private JTabbedPane tabbedPane;
    JPanel contentPane;
    private SelectorListComponent energySelectorListComponent;
    private EnergyElementComponent energyElementComponent;
    private SelectorListComponent vehicleSelectorListComponent;
    private VehicleElementComponent vehicleElementComponent;
    private IntervalsInput kilometersIntervalInput;
    private IntervalsInput datesIntervalInput;
    private IntervalsInput loanRatesInput;
    private PassengerBonusComponent passengerBonusComponent;
    private GeneralSettingsComponent generalSettingsComponent;

    public SettingsForm(Config config, Window window, TempConfig tempConfig) {
        this.config = config;
        this.window = window;
        this.tempConfig = tempConfig;
        initComponents();
    }

    // This constructor is used by IntelliJ IDEA's GUI designer
    @SuppressWarnings("unused")
    public SettingsForm() {
        this(Window.INSTANCE.config, Window.INSTANCE, new TempConfig(Window.INSTANCE.config));
    }

    private void initComponents() {

    }

    public void save() {
        tempConfig.copy();
        config.save();
    }

    private void createUIComponents() {
        generalSettingsComponent = new GeneralSettingsComponent(tempConfig);

        vehicleElementComponent = new VehicleElementComponent(tempConfig);
        vehicleSelectorListComponent = new SelectorListComponent<>(vehicleElementComponent);

        energyElementComponent = new EnergyElementComponent(tempConfig);
        energySelectorListComponent = new SelectorListComponent<>(energyElementComponent);

        kilometersIntervalInput = new IntervalsInput<>(tempConfig.getKilometersPerYear(), (IntervalEntry::new));

        datesIntervalInput = new IntervalsInput<>(tempConfig.getVehicleAges(), (YearIntervalEntry::new));

        loanRatesInput = new IntervalsInput<>(tempConfig.getLoanRates(), (LoanRateEntry::new));

        passengerBonusComponent = new PassengerBonusComponent(tempConfig);
    }
}
