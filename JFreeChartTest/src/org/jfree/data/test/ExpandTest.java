package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.expand(Range range, double lowerMargin, double upperMargin)

public class ExpandTest {
    private static final double EPS = 1e-9;

    // Testing: expansion with positive margins
    // Partition: Valid range with positive margins
    // Expected: [-1, 12]
    @Test
    void expand_PositiveMargins_ShouldExpandBothSides() {
        Range base = new Range(0, 10);

        Range expanded = Range.expand(base, 0.1, 0.2);

        assertNotNull(expanded);
        assertEquals(-1.0, expanded.getLowerBound(), EPS);
        assertEquals(12.0, expanded.getUpperBound(), EPS);
    }

    // Testing: boundary case with zero margins
    // Partition: Zero margins (boundary case)
    // Expected: [0, 10]
    @Test
    void expand_ZeroMargins_ShouldReturnSameBounds() {
        Range base = new Range(0, 10);

        Range expanded = Range.expand(base, 0.0, 0.0);

        assertNotNull(expanded);
        assertEquals(0.0, expanded.getLowerBound(), EPS);
        assertEquals(10.0, expanded.getUpperBound(), EPS);
    }

    // Testing: exception handling for null range input
    // Partition: Null range object
    // Expected: InvalidParameterException
    @Test
    void expand_NullRange_ShouldThrowInvalidParameterException() {
        assertThrows(java.security.InvalidParameterException.class, () -> {
            Range.expand(null, 0.1, 0.2);
        });
    }
}