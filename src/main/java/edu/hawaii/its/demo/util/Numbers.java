package edu.hawaii.its.demo.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Numbers {

    public static final BigDecimal ZERO_AMOUNT = new BigDecimal("0.00");

    // Private contructor to prevent instantiation.
    private Numbers() {
        // Empty.
    }

    public static long parseLong(String s) {
        return parseLong(s, 0);
    }

    public static long parseLong(String s, long defaultValue) {
        if (s == null) {
            return defaultValue;
        }

        String val = s.trim();
        if (val.length() == 0) {
            return defaultValue;
        }

        return Long.parseLong(val);
    }

    // Slight variation on StringUtils.isNumeric method.
    public static boolean isNumeric(final String str) {
        if (str == null) {
            return false;
        }

        StringBuilder sb = new StringBuilder(str.trim());
        int size = sb.length();
        if (size == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!Character.isDigit(sb.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPositiveDouble(String s) {
        if (s == null) {
            return false;
        }
        try {
            return Double.parseDouble(s) >= 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isPositive(float value) {
        return value >= 0;
    }

    public static boolean isPositive(double value) {
        return value >= 0;
    }

    public static String format(BigDecimal value) {
        try {
            return new DecimalFormat("###0.00 ;###0.00-").format(value);
        } catch (Exception e) {
            return "0.00 ";
        }
    }
}
