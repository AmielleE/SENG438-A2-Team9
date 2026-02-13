package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

// Test class for DataUtilities.createNumberArray()
// This is a regular test because it operates on a primitive array and does not require mocking

public class CreateNumberArrayTest {

    // Testing: normal behaviour with a valid array of doubles
    // Partition: Valid input array
    // Expected: Returns a Number array with the same values
    @Test
    void testCreateNumberArray_ValidInput_ShouldReturnCorrectArray() {
        double[] input = {1.0, 2.0, 3.0};

        Number[] result = DataUtilities.createNumberArray(input);

        assertArrayEquals(new Number[]{1.0, 2.0, 3.0}, result);
    }

    // Testing: null input
    // Partition: Invalid input (null array)
    // Expected: IllegalArgumentException thrown
    @Test
    void testCreateNumberArray_NullInput_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.createNumberArray(null);
        });
    }
}
