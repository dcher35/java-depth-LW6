package com.edu.chmnu.ki_123.c3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MathCalcGraphTest {

    // Тест для перевірки обчислення значення функції
    @Test
    public void testFunctionCalculation() {
        double x = 8.1;
        double a = 2.5, b = 7.7, c = -4.32;

        double expected = Math.exp(a * Math.cos(x + 2)) - (Math.exp(-Math.sin(b * x))) / (c - Math.cbrt(x));

        assertFalse(Double.isNaN(expected) || Double.isInfinite(expected),
                "Результат не повинен бути NaN або безкінечністю");
        assertEquals(0.3888, expected, 0.0001, "Очікуване значення не збігається з обчисленим значенням");
    }

    // Тест для перевірки знаходження мінімуму
    @Test
    public void testFindExtremum() {
        double a = 2.5, b = 7.7, c = -4.32;
        double minF = Double.MAX_VALUE;
        double minX = 0;
        boolean foundMin = false;

        for (double x = -10; x <= 10; x += 0.1) {
            try {
                double f = Math.exp(a * Math.cos(x + 2)) - (Math.exp(-Math.sin(b * x))) / (c - Math.cbrt(x));

                if (Double.isNaN(f) || Double.isInfinite(f)) continue;

                if (f < minF) {
                    minF = f;
                    minX = x;
                    foundMin = true;
                }
            } catch (Exception ignored) {
            }
        }

        assertTrue(foundMin, "Мінімальне значення повинно бути знайдено");
        assertEquals(7.5, minX, 0.001, "Очікуване мінімальне x значення не збігається");
    }
}
