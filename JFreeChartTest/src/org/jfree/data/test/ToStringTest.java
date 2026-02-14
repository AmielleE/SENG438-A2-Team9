package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.toString()

public class ToStringTest {

    // Testing: string output contains both bounds
    // Partition: Normal string representation
    // Expected: Output should contain -1 and 1
    @Test
    void toString_ShouldContainLowerAndUpperBounds() {
        Range r = new Range(-1, 1);

        String s = r.toString();

        assertNotNull(s);
        assertTrue(s.contains("-1"), "toString() should include the lower bound");
        assertTrue(s.contains("1"), "toString() should include the upper bound");
    }
}