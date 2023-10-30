package io.github.mireole.asynconf2023.gui;

import io.github.mireole.asynconf2023.backend.Calculator;
import io.github.mireole.asynconf2023.backend.Config;

import javax.swing.*;

public class Window extends JFrame {
    public static Window INSTANCE;
    public final Config config;
    private final Calculator calculator;
    public Window(Config config) {
        super("Calculateur de taux d'emprunt");
        INSTANCE = this;
        this.config = config;
        this.calculator = new Calculator(config);
        // Window setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reloadCalculator();
        this.setVisible(true);
    }

    public void reloadCalculator() {
        // We have to reinitialize it in case settings were changed
        MainCalculatorForm form = new MainCalculatorForm(config, calculator, this);
        this.setContentPane(form.contentPane);
        this.pack();
    }

    public void openSettings() {
        SettingsDialog dialog = new SettingsDialog(this);
        dialog.pack();
        dialog.setVisible(true);
    }

}
