package edu.hawaii.its.demo.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

import org.junit.Test;

public class NumbersTest {

    @Test
    public void parseInt() {

        assertEquals(0, Numbers.parseLong(null));
        assertEquals(0, Numbers.parseLong(""));
        assertEquals(0, Numbers.parseLong(" "));

        assertEquals(1, Numbers.parseLong("1"));
        assertEquals(1, Numbers.parseLong("1 "));
        assertEquals(1, Numbers.parseLong(" 1"));
        assertEquals(1, Numbers.parseLong(" 1 "));
        assertEquals(-1, Numbers.parseLong(" -1 "));

        assertEquals(0, Numbers.parseLong(null));
        assertEquals(0, Numbers.parseLong(""));
        assertEquals(0, Numbers.parseLong("    "));
        assertEquals(0, Numbers.parseLong("\t"));

        assertEquals(1, Numbers.parseLong("1"));
        assertEquals(99, Numbers.parseLong("99"));
        assertEquals(666, Numbers.parseLong("666"));
        assertEquals(99, Numbers.parseLong("    99   "));
        assertEquals(-1, Numbers.parseLong("-1"));

        assertEquals(1, Numbers.parseLong("1"));
        assertEquals(666, Numbers.parseLong("666"));
        assertEquals(987654321, Numbers.parseLong("987654321"));
        assertEquals(-1, Numbers.parseLong("-1"));
        assertEquals(0, Numbers.parseLong("0"));
        assertEquals(0, Numbers.parseLong(""));
        assertEquals(0, Numbers.parseLong(null));

        // Current exception behavor.
        // Handling might change, though.
        try {
            assertEquals(0, Numbers.parseLong("damn"));
            fail("Should fail before here.");
        } catch (Exception ex) {
            assertTrue(ex instanceof NumberFormatException);
        }

        // With default argument.
        assertEquals(3, Numbers.parseLong(null, 3));
        assertEquals(2, Numbers.parseLong("", 2));
        assertEquals(1, Numbers.parseLong(" ", 1));
        assertEquals(4, Numbers.parseLong("    ", 4));
        assertEquals(5, Numbers.parseLong("\t", 5));
        assertEquals(0, Numbers.parseLong("0", 9));
    }

    @Test
    public void isNumeric() {
        assertFalse(Numbers.isNumeric(null));
        assertFalse(Numbers.isNumeric(""));
        assertFalse(Numbers.isNumeric("  "));
        assertFalse(Numbers.isNumeric("\t"));
        assertFalse(Numbers.isNumeric("A"));
        assertFalse(Numbers.isNumeric("1111a"));
        assertFalse(Numbers.isNumeric("a1111"));

        assertFalse(Numbers.isNumeric("-1")); // Note this result.
        assertFalse(Numbers.isNumeric("1.1")); // This, too.

        assertTrue(Numbers.isNumeric("1"));
        assertTrue(Numbers.isNumeric("1 "));
        assertTrue(Numbers.isNumeric(" 1"));
        assertTrue(Numbers.isNumeric(" 1 "));
        assertTrue(Numbers.isNumeric("99"));
    }

    @Test
    public void isPositive() {
        assertFalse(Numbers.isPositive(-1d));
        assertTrue(Numbers.isPositive(0d));
        assertTrue(Numbers.isPositive(1d));
    }

    @Test
    public void isPositiveDouble() {
        float value = 0f;
        assertTrue(Numbers.isPositiveDouble(String.valueOf(value)));
        assertTrue(Numbers.isPositive(value));

        value = 0.0f;
        assertTrue(Numbers.isPositiveDouble(String.valueOf(value)));
        assertTrue(Numbers.isPositive(value));

        value = -0.0001f;
        assertFalse(Numbers.isPositiveDouble(String.valueOf(value)));
        assertFalse(Numbers.isPositive(value));

        value = -1f;
        assertFalse(Numbers.isPositiveDouble(String.valueOf(value)));
        assertFalse(Numbers.isPositive(value));

        value = -1.0f;
        assertFalse(Numbers.isPositiveDouble(String.valueOf(value)));
        assertFalse(Numbers.isPositive(value));

        value = +0.0f;
        assertTrue(Numbers.isPositiveDouble(String.valueOf(value)));
        assertTrue(Numbers.isPositive(value));

        value = +0.0001f;
        assertTrue(Numbers.isPositiveDouble(String.valueOf(value)));
        assertTrue(Numbers.isPositiveDouble("  " + value));
        assertTrue(Numbers.isPositiveDouble(value + " "));
        assertTrue(Numbers.isPositiveDouble(" " + value + " "));
        assertTrue(Numbers.isPositive(value));

        assertFalse(Numbers.isPositiveDouble(""));
        assertFalse(Numbers.isPositiveDouble(" "));
        assertFalse(Numbers.isPositiveDouble("\t"));
        assertFalse(Numbers.isPositiveDouble("\n"));
        assertFalse(Numbers.isPositiveDouble(null));
    }

    @Test
    public void round() {
        assertEquals(1, Math.round(1f));
        assertEquals(1, Math.round(1.0f));
        assertEquals(1, Math.round(1.000f));
        assertEquals(1, Math.round(0.5f));
        assertEquals(1, Math.round(0.99f));
        assertEquals(2, Math.round(2f));
        assertEquals(2, Math.round(2.000f));
        assertEquals(2, Math.round(2.001f));

        assertEquals(133, Math.round(132.8f));
        assertEquals(133, Math.round(132.7f));
        assertEquals(133, Math.round(132.6f));
        assertEquals(133, Math.round(132.5f));
        assertEquals(132, Math.round(132.49f));
    }

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor<Numbers> constructor = Numbers.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void format() {
        BigDecimal value = new BigDecimal("0.00");
        assertThat(value, equalTo(new BigDecimal("0.00")));
        assertThat(Numbers.format(value), equalTo("0.00 "));

        value = new BigDecimal("0.01");
        assertThat(value, equalTo(new BigDecimal("0.01")));
        assertThat(value, equalTo(BigDecimal.valueOf(0.01)));
        assertThat(Numbers.format(value), equalTo("0.01 "));

        value = new BigDecimal("0.21");
        assertThat(value, equalTo(new BigDecimal("0.21")));
        assertThat(value, equalTo(BigDecimal.valueOf(0.21)));
        assertThat(Numbers.format(value), equalTo("0.21 "));

        value = new BigDecimal("3.21");
        assertThat(value, equalTo(new BigDecimal("3.21")));
        assertThat(value, equalTo(BigDecimal.valueOf(3.21)));
        assertThat(Numbers.format(value), equalTo("3.21 "));

        value = new BigDecimal("03.21");
        assertThat(value, equalTo(new BigDecimal("3.21")));
        assertThat(value, equalTo(BigDecimal.valueOf(3.21)));
        assertThat(Numbers.format(value), equalTo("3.21 "));

        value = new BigDecimal("-3.21");
        assertThat(value, equalTo(new BigDecimal("-3.21")));
        assertThat(value, equalTo(BigDecimal.valueOf(-3.21)));
        assertThat(Numbers.format(value), equalTo("3.21-"));

        value = new BigDecimal("-003.21");
        assertThat(value, equalTo(new BigDecimal("-003.21")));
        assertThat(value, equalTo(BigDecimal.valueOf(-3.21)));
        assertThat(Numbers.format(value), equalTo("3.21-"));

        // Null comes out as zero.
        value = null;
        assertThat(value, equalTo(null));
        assertThat(Numbers.format(value), equalTo("0.00 "));
    }
}
