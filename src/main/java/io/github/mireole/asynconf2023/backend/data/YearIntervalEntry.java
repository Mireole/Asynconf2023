package io.github.mireole.asynconf2023.backend.data;

import java.time.Year;

/*
    * Essentially the same as IntervalEntry, the only difference is that max is capped at the current year
 */
public class YearIntervalEntry extends IntervalEntry{
    public YearIntervalEntry(int start, int end, float ecoScore) {
        super(start, end, ecoScore);
    }

    @Override
    public int getMaxValue() {
        // year + 1 in case a car is bought in the current year
        if (super.getMaxValue() > Year.now().getValue() + 1 || super.getMaxValue() == -1) {
            return Year.now().getValue() + 1;
        }
        return super.getMaxValue();
    }

    // We also have to change getStep() otherwise it might lower the interval for no reason
    @Override
    public int getStep() {
        if (super.getMaxValue() == -1 || getMinValue() == -1) {
            return Integer.MAX_VALUE;
        }
        return getMaxValue() - getMinValue();
    }
}
