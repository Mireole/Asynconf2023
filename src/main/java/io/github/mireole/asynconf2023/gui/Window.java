package io.github.mireole.asynconf2023.gui;

import io.github.mireole.asynconf2023.backend.Calculator;
import io.github.mireole.asynconf2023.backend.Config;

import javax.swing.*;
import java.util.Objects;

public class Window extends JFrame {
    public static Window INSTANCE;
    public final Config config;
    private final Calculator calculator;
    public Window(Config config) {
        super("Calculateur de taux d'emprunt");
        INSTANCE = this;
        this.config = config;
        this.calculator = new Calculator(config);
        // Icon setup
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../calculator.png")));
        setIconImage(icon.getImage());
        // Window setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reloadCalculator();
        setMinimumSize(this.getSize());
        setResizable(false);
        setLocationRelativeTo(null); // Center the window
        this.setVisible(true);
    }

    public void reloadCalculator() {
        // We have to reinitialize it in case settings were changed
        MainCalculatorForm form = new MainCalculatorForm(config, calculator, this);
        this.setContentPane(form.contentPane);
        this.pack();
    }

    public void openSettings() {
        SettingsDialog dialog = new SettingsDialog(this, config);
        dialog.pack();
        dialog.setMinSize();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

}
