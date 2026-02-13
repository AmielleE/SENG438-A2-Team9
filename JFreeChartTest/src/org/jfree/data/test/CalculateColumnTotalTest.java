package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


// Test class for DataUtilities.calculateColumnTotal()
// This is a mock test because Values2D is an interface, so we simulate it using Mockito.

public class CalculateColumnTotalTest {

    private Values2D values;

    @BeforeEach
    void setUp() {
        values = mock(Values2D.class);
    }

    // Testing: normal behaviour where column contains valid numbers
    // Partition: Valid data, normal column index
    // Expected: Sum of column values
    @Test
    void testCalculateColumnTotal_ValidData_ShouldReturnSum() {
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(5.0);
        when(values.getValue(1, 0)).thenReturn(3.0);

        double result = DataUtilities.calculateColumnTotal(values, 0);

        // Expected total is 5 + 3 = 8
        assertEquals(8.0, result, 0.0001);
    }

    @Test
    void testCalculateColumnTotal_NullData_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.calculateColumnTotal(null, 0);
        });
    }
}
