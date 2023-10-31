package io.github.mireole.asynconf2023.backend.data;

import io.github.mireole.asynconf2023.gui.ComboBoxEntry;

public record VehicleTypeEntry(String name, String averageWeight, float ecoScore) implements ComboBoxEntry {

    // Name used in the combo box
    public String getVisualName() {
        return name + " (" + averageWeight + ")";
    }

    @Override
    public String toString() {
        return getVisualName();
    }
}
