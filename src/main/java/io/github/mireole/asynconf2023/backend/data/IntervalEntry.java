package io.github.mireole.asynconf2023.backend.data;

public class IntervalEntry {
    private final int start;
    private final int end;
    private final float ecoScore;

    public IntervalEntry(int start, int end, float ecoScore) {
        this.start = start;
        this.end = end;
        this.ecoScore = ecoScore;
    }
}
