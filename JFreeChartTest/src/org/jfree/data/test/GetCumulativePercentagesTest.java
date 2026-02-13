package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test class for DataUtilities.getCumulativePercentages()
// This is a mock test because KeyedValues is an interface, so we simulate it using Mockito.

public class GetCumulativePercentagesTest {

    private KeyedValues values;

    @BeforeEach
    void setUp() {
        values = mock(KeyedValues.class);
    }

    // Testing: normal behaviour with valid KeyedValues
    // Partition: Valid data, normal keys and values
    // Expected: Cumulative percentages calculated correctly
    @Test
    void testGetCumulativePercentages_ValidData_ShouldReturnCorrectPercentages() {
        when(values.getItemCount()).thenReturn(3);
        when(values.getKey(0)).thenReturn(0);
        when(values.getKey(1)).thenReturn(1);
        when(values.getKey(2)).thenReturn(2);

        when(values.getValue(0)).thenReturn(5.0);
        when(values.getValue(1)).thenReturn(9.0);
        when(values.getValue(2)).thenReturn(2.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertEquals(0.3125, result.getValue(0));
        assertEquals(0.875, result.getValue(1));
        assertEquals(1.0, result.getValue(2));
    }

    // Testing: null input
    // Partition: Invalid input
    // Expected: IllegalArgumentException thrown
    @Test
    void testGetCumulativePercentages_NullData_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.getCumulativePercentages(null);
        });
    }
}