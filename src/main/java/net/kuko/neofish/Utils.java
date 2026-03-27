package net.kuko.neofish;

public class Utils {
    public static int packARGB(int alpha, int red, int green, int blue) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
