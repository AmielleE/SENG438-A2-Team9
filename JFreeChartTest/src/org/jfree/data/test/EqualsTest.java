package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.equals(Object obj)

public class EqualsTest {

    // Testing: two Range objects with same bounds are equal
    // Partition: Two Range objects with same bounds
    // Expected: true
    @Test
    void equals_SameBounds_ShouldReturnTrue() {
        Range r1 = new Range(0, 1);
        Range r2 = new Range(0, 1);
        assertTrue(r1.equals(r2));
    }

    // Testing: two Range objects with different bounds are not equal
    // Partition: Two Range objects with different bounds
    // Expected: false
    @Test
    void equals_DifferentBounds_ShouldReturnFalse() {
        Range r1 = new Range(0, 1);
        Range r2 = new Range(0, 2);
        assertFalse(r1.equals(r2));
    }

    // Testing: robustness when comparing to null or different type
    // Partition: Null object or different type
    // Expected: false
    @Test
    void equals_NullOrDifferentType_ShouldReturnFalse() {
        Range r = new Range(0, 1);
        assertFalse(r.equals(null));
        assertFalse(r.equals((Object) "not a range"));
    }
}