package io.github.mireole.asynconf2023.gui;

import io.github.mireole.asynconf2023.backend.Config;
import io.github.mireole.asynconf2023.backend.TempConfig;
import io.github.mireole.asynconf2023.backend.data.IntervalEntry;
import io.github.mireole.asynconf2023.backend.data.LoanRateEntry;
import io.github.mireole.asynconf2023.backend.data.YearIntervalEntry;
import io.github.mireole.asynconf2023.gui.components.*;

import javax.swing.*;

@SuppressWarnings("rawtypes")
public class SettingsForm {
    private final Config config;
    private final TempConfig tempConfig;
    JPanel contentPane;
    private JTabbedPane tabbedPane;
    private SelectorListComponent energySelectorListComponent;
    private EnergyElementComponent energyElementComponent;
    private SelectorListComponent vehicleSelectorListComponent;
    private VehicleElementComponent vehicleElementComponent;
    private IntervalsInput kilometersIntervalInput;
    private IntervalsInput datesIntervalInput;
    private IntervalsInput loanRatesInput;
    private PassengerBonusComponent passengerBonusComponent;
    private GeneralSettingsComponent generalSettingsComponent;

    public SettingsForm(Config config, TempConfig tempConfig) {
        this.config = config;
        this.tempConfig = tempConfig;
    }

    // This constructor is used by IntelliJ IDEA's GUI designer
    @SuppressWarnings("unused")
    public SettingsForm() {
        this(Window.INSTANCE.config, new TempConfig(Window.INSTANCE.config));
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
