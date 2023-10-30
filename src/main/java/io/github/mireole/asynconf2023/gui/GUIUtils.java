package io.github.mireole.asynconf2023.gui;

import java.util.List;

/**
 * A utility class for GUI-related operations.
 */
public class GUIUtils {
    /**
     * Interpolates the color between two given colors based on a given value and its maximum.
     *
     * @param colorStart the start color
     * @param colorEnd the end color
     * @param x the value used for interpolation
     * @param max the maximum value for x
     * @return the interpolated color
     */
    public static int interpolateColor(int colorStart, int colorEnd, float x, float max) {
        int rStart = (colorStart >> 16) & 0xFF;
        int gStart = (colorStart >> 8) & 0xFF;
        int bStart = colorStart & 0xFF;

        int rEnd = (colorEnd >> 16) & 0xFF;
        int gEnd = (colorEnd >> 8) & 0xFF;
        int bEnd = colorEnd & 0xFF;

        int rInterp = (int) ((rEnd - rStart) * x / max + rStart);
        int gInterp = (int) ((gEnd - gStart) * x / max + gStart);
        int bInterp = (int) ((bEnd - bStart) * x / max + bStart);

        return rInterp << 16 | gInterp << 8 | bInterp;
    }

    /**
     * Returns the first ComboBoxEntry object from the given list of entries that matches the provided string.
     * Used to get the ComboBoxEntry object from the selected string in a JComboBox.
     *
     * @param s      the string to match
     * @param entries the list of ComboBoxEntry objects to search in
     * @return the first ComboBoxEntry object that matches the provided string, or null if no match is found
     */
    public static <E extends ComboBoxEntry> E getComboBoxEntry(String s, List<E> entries) {
        for (E entry : entries) {
            if (entry.matches(s)) {
                return entry;
            }
        }
        return null;

    }

    /**
     * Returns the first SpinnerEntry object from the given list of entries that matches the provided integer.
     * Used to get the SpinnerEntry object from the selected integer in a JSpinner.
     *
     * @param i       the integer to match
     * @param entries the list of SpinnerEntry objects to search in
     * @return the first SpinnerEntry object that matches the provided integer, or null if no match is found
     */
    public static <E extends SpinnerEntry> E getSpinnerEntry(int i, List<E> entries) {
        for (E entry : entries) {
            if (entry.matches(i)) {
                return entry;
            }
        }
        return null;
    }
}
