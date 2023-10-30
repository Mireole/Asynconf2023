package io.github.mireole.asynconf2023.gui;

import javax.swing.*;
import java.awt.event.*;

public class SettingsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonApply;
    private SettingsForm settingsForm;

    public SettingsDialog(JFrame parent) {
        super(parent, "Paramètres", true);
        setContentPane(contentPane);

        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        buttonApply.addActionListener(e -> onApply());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        onApply();
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void onApply() {
        settingsForm.save();
        // Refresh the calculator
        Window.INSTANCE.reloadCalculator();
    }
}
