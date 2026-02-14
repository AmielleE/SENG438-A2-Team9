package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.constrain(double value)

public class ConstrainTest {
    private static final double EPS = 1e-9;

    // Testing: value inside range stays unchanged
    // Partition: Value inside range
    // Expected: 0.25
    @Test
    void constrain_ValueInsideRange_ShouldReturnSameValue() {
        Range r = new Range(-1, 1);
        assertEquals(0.25, r.constrain(0.25), EPS);
    }

    // Testing: value below lower bound clamps to lower bound
    // Partition: Value below lower bound
    // Expected: -1
    @Test
    void constrain_ValueBelowLower_ShouldReturnLowerBound() {
        Range r = new Range(-1, 1);
        assertEquals(-1.0, r.constrain(-5.0), EPS);
    }

    // Testing: value above upper bound clamps to upper bound
    // Partition: Value above upper bound
    // Expected: 1
    @Test
    void constrain_ValueAboveUpper_ShouldReturnUpperBound() {
        Range r = new Range(-1, 1);
        assertEquals(1.0, r.constrain(5.0), EPS);
    }
}