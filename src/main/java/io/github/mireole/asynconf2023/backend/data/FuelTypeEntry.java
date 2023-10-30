package io.github.mireole.asynconf2023.backend.data;

public class FuelTypeEntry {
    private final String name;
    private final float ecoScore;

    public FuelTypeEntry(String name, float ecoScore) {
        this.name = name;
        this.ecoScore = ecoScore;
    }
}
