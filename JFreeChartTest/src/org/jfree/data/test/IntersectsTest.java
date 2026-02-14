package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.intersects(double lower, double upper)

public class IntersectsTest {

    // Testing: interval completely left of range
    // Partition: Interval completely left of range
    // Expected: false
    @Test
    void intersects_DisjointLeft_ShouldReturnFalse() {
        Range r = new Range(0, 10);
        assertFalse(r.intersects(-5, -1));
    }

    // Testing: interval completely right of range
    // Partition: Interval completely right of range
    // Expected: false
    @Test
    void intersects_DisjointRight_ShouldReturnFalse() {
        Range r = new Range(0, 10);
        assertFalse(r.intersects(11, 20));
    }

    // Testing: partially overlapping interval returns true
    // Partition: Partially overlapping interval
    // Expected: true
    @Test
    void intersects_Overlapping_ShouldReturnTrue() {
        Range r = new Range(0, 10);
        assertTrue(r.intersects(5, 15));
    }

    // Testing: interval fully contained within range returns true
    // Partition: Interval fully contained within range
    // Expected: true
    @Test
    void intersects_ContainedInterval_ShouldReturnTrue() {
        Range r = new Range(0, 10);
        assertTrue(r.intersects(2, 3));
    }

    // Testing: invalid interval where lower > upper
    // Partition: Invalid interval lower > upper
    // Expected: false
    @Test
    void intersects_InvalidIntervalLowerGreaterThanUpper_ShouldReturnFalse() {
        Range r = new Range(0, 10);
        assertFalse(r.intersects(5, 4));
    }
}