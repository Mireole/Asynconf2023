package io.github.mireole.asynconf2023.backend.data;

public class LoanRateEntry {
    private final int start;
    private final int end;
    private final float value;

    public LoanRateEntry(int start, int end, float value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }
}
