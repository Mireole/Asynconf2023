package io.github.mireole.asynconf2023.gui;

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
    public int interpolateColor(int colorStart, int colorEnd, float x, float max) {
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
}
