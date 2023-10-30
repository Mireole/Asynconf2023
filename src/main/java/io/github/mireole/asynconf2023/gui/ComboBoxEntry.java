package io.github.mireole.asynconf2023.gui;

public interface ComboBoxEntry {
    // Name used in the combo box
    String getVisualName();

    // Used to find which entry is selected in the combo box
    default boolean matches(String visualName) {
        return visualName.equals(getVisualName());
    }
}
