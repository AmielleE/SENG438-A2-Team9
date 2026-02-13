package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

// Test class for DataUtilities.createNumberArray2D()
// This is a regular test because it operates on a primitive 2D array and does not require mocking

public class CreateNumberArray2DTest {

    // Testing: normal behaviour with a valid 2D array of doubles
    // Partition: Valid input 2D array
    // Expected: Returns a 2D Number array with the same values
    @Test
    void testCreateNumberArray2D_ValidInput_ShouldReturnCorrect2DArray() {
        double[][] input = {
                {1.0, 2.0},
                {3.0, 4.0}
        };

        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(1.0, result[0][0]);
        assertEquals(4.0, result[1][1]);
    }

    // Testing: null input
    // Partition: Invalid input (null array)
    // Expected: IllegalArgumentException thrown
    @Test
    void testCreateNumberArray2D_NullInput_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.createNumberArray2D(null);
        });
    }
}
