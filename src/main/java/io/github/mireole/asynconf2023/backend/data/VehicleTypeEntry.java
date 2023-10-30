package io.github.mireole.asynconf2023.backend.data;

public class VehicleTypeEntry {
    private final String name;
    private final String averageWeight;
    private final float ecoScore;

    public VehicleTypeEntry(String name, String averageWeight, float ecoScore) {
        this.name = name;
        this.averageWeight = averageWeight;
        this.ecoScore = ecoScore;
    }

}
