package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.hashCode()

public class HashCodeTest {

    // Testing: hashCode contract for equal objects
    // Partition: Equal objects (same bounds)
    // Expected: Same hash code for equivalent objects
    @Test
    void hashCode_EqualObjects_ShouldHaveSameHashCode() {
        Range r1 = new Range(0, 1);
        Range r2 = new Range(0, 1);

        assertTrue(r1.equals(r2));
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}