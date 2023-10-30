package io.github.mireole.asynconf2023.backend.data;

import io.github.mireole.asynconf2023.gui.SpinnerEntry;

public class IntervalEntry implements SpinnerEntry {
    private final int start;
    private final int end;
    private final float ecoScore;

    public IntervalEntry(int start, int end, float ecoScore) {
        this.start = start;
        this.end = end;
        this.ecoScore = ecoScore;
    }

    @Override
    public int getMinValue() {
        return start;
    }

    @Override
    public int getMaxValue() {
        return end;
    }
}
