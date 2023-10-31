package io.github.mireole.asynconf2023.gui;

import com.github.weisj.darklaf.LafManager;
import io.github.mireole.asynconf2023.backend.Config;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SettingsDialog extends JDialog {
    private final Config config;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonApply;
    private SettingsForm settingsForm;

    public SettingsDialog(JFrame parent, Config config) {
        super(parent, "Paramètres", true);
        this.config = config;
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

    public void setMinSize() {
        this.setMinimumSize(this.getSize());
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
        if (config.getTheme().theme != LafManager.getTheme()) {
            // Reload the look and feel for the main window and the settings window
            LafManager.setTheme(config.getTheme().theme);
            LafManager.install();
            SwingUtilities.updateComponentTreeUI(Window.INSTANCE);
            SwingUtilities.updateComponentTreeUI(this);
        }
        // Refresh the calculator
        Window.INSTANCE.reloadCalculator();
    }
}
