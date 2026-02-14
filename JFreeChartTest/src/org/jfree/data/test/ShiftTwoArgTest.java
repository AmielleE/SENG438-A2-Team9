package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.shift(Range base, double delta)

public class ShiftTwoArgTest {
    private static final double EPS = 1e-9;

    // Testing: normal shift with positive delta
    // Partition: Normal shift with positive delta
    // Expected: [3, 5]
    @Test
    void shift_TwoArg_ShouldMoveBothBoundsByDelta() {
        Range base = new Range(1, 3);

        Range shifted = Range.shift(base, 2);

        assertNotNull(shifted);
        assertEquals(3.0, shifted.getLowerBound(), EPS);
        assertEquals(5.0, shifted.getUpperBound(), EPS);
    }
}