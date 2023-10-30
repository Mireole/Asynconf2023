package io.github.mireole.asynconf2023.backend.data;

import io.github.mireole.asynconf2023.gui.ComboBoxEntry;

public class VehicleTypeEntry implements ComboBoxEntry {
    private final String name;
    private final String averageWeight;
    private final float ecoScore;

    public VehicleTypeEntry(String name, String averageWeight, float ecoScore) {
        this.name = name;
        this.averageWeight = averageWeight;
        this.ecoScore = ecoScore;
    }

    // Name used in the combo box
    public String getVisualName() {
        return name + " (" + averageWeight + ")";
    }

    public float getEcoScore() {
        return ecoScore;
    }

    public String getName() {
        return name;
    }

    public String getAverageWeight() {
        return averageWeight;
    }

    @Override
    public String toString() {
        return getVisualName();
    }
}
