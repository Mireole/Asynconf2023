package io.github.mireole.asynconf2023.backend.data;

import io.github.mireole.asynconf2023.gui.ComboBoxEntry;

public class FuelTypeEntry implements ComboBoxEntry {
    private final String name;
    private final float ecoScore;

    public FuelTypeEntry(String name, float ecoScore) {
        this.name = name;
        this.ecoScore = ecoScore;
    }

    @Override
    public String getVisualName() {
        return name;
    }

    public float getEcoScore() {
        return ecoScore;
    }

    @Override
    public String toString() {
        return name;
    }
}
