package io.github.mireole.asynconf2023.backend.data;

import io.github.mireole.asynconf2023.gui.SpinnerEntry;

public class LoanRateEntry extends IntervalEntry implements SpinnerEntry {
    public LoanRateEntry(int start, int end, float value) {
        super(start, end, value);
    }

    @Override
    public boolean matches(int value) {
        return value >= getMinValue() && value <= getMaxValue();
    }
}
