package io.github.mireole.asynconf2023.gui;

import io.github.mireole.asynconf2023.backend.data.Config;

import javax.swing.*;

public class Window extends JFrame {
    public Window(Config config) {
        super("Calculateur de taux d'emprunt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainCalculatorForm form = new MainCalculatorForm();
        this.setContentPane(form.contentPane);
        this.pack();
        this.setVisible(true);
    }
}
