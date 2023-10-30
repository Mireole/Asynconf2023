package io.github.mireole.asynconf2023.gui;

import io.github.mireole.asynconf2023.backend.Config;
import io.github.mireole.asynconf2023.backend.TempConfig;
import io.github.mireole.asynconf2023.gui.components.VehiclesComponent;

import javax.swing.*;

public class SettingsForm {
    private final Config config;
    private final Window window;
    private final TempConfig tempConfig;
    private JTabbedPane tabbedPane;
    JPanel contentPane;
    private VehiclesComponent vehicles;

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
        vehicles = new VehiclesComponent(tempConfig);
    }
}
