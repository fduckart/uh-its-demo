package edu.hawaii.its.demo.util;

public class Threads {

    // Private constructor; prevent instantiation.
    private Threads() {
        // Emtpy.
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            // Ignored.
        }
    }
}
