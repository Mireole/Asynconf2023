package io.github.mireole.asynconf2023.gui;

import io.github.mireole.asynconf2023.backend.Calculator;
import io.github.mireole.asynconf2023.backend.Config;

import javax.swing.*;

public class Window extends JFrame {
    public Window(Config config) {
        super("Calculateur de taux d'emprunt");
        // Window setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainCalculatorForm form = new MainCalculatorForm(config, new Calculator(config));
        this.setContentPane(form.contentPane);
        this.pack();
        this.setVisible(true);
    }
}
