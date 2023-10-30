package io.github.mireole.asynconf2023.backend.data;

import io.github.mireole.asynconf2023.gui.SpinnerEntry;

public class LoanRateEntry implements SpinnerEntry {
    private final int start;
    private final int end;
    private final float value;

    public LoanRateEntry(int start, int end, float value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    @Override
    public int getMinValue() {
        return start;
    }

    public float getValue() {
        return value;
    }

    @Override
    public int getMaxValue() {
        return end;
    }

    @Override
    public boolean matches(int value) {
        return value >= getMinValue() && value <= getMaxValue();
    }
}
