package io.github.mireole.asynconf2023.gui;

public interface SpinnerEntry {
    int getMinValue();

    int getMaxValue();

    default int getStep() {
        if (getMaxValue() == -1 || getMinValue() == -1) {
            return Integer.MAX_VALUE;
        }
        return getMaxValue() - getMinValue();
    }

    default boolean matches(int value) {
        return value >= getMinValue() && value < getMaxValue();
    }
}
