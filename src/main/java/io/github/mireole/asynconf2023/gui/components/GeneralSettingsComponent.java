package io.github.mireole.asynconf2023.gui.components;

import io.github.mireole.asynconf2023.backend.TempConfig;
import io.github.mireole.asynconf2023.gui.Themes;

import javax.swing.*;

@SuppressWarnings("rawtypes")
public class GeneralSettingsComponent {
    private final TempConfig tempConfig;
    private JLabel themeLabel;
    private JComboBox theme;
    private JPanel contentPane;

    public GeneralSettingsComponent(TempConfig tempConfig) {
        this.tempConfig = tempConfig;
    }

    private void createUIComponents() {
        theme = new JComboBox<>(Themes.values());
        if (tempConfig.getTheme() != null) {
            theme.setSelectedItem(tempConfig.getTheme());
        }

        createListeners();
    }

    private void createListeners() {
        theme.addActionListener(e -> tempConfig.setTheme((Themes) theme.getSelectedItem()));
    }
}
