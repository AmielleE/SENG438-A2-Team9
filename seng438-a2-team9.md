**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**
| Group: 9      |
|-----------------|
| Amielle El Makhzoumi |   
| Fatma Alzubaidi      |   
| Josral Frederick     |   
| Faris Janjua         |   
| Erioluwa Olubadejo   |  

# 1 Introduction

Text…

# 2 Detailed description of unit test strategy

// including the input partitions you have designed

# 3 Test cases developed

Text…

// write down the name of the test methods and classes. Organize the based on
the source code method // they test. identify which tests cover which partitions
you have explained in the test strategy section //above
# Range Test.java
## 1.getCentralValue(): boolean
     - Test Case: TC_GETCENTRALVALUE_01
     - Method: getCentralValue()
     - Partition: Normal symmetric range [-1, 1]
     - Expected: Central value should be 0
     - Result: Passed
## 2. contains(double): boolean
1. Test Case: testContains_ValueInsideRange_ReturnsTrue
- Method: contains(double value)
- Partition: Value inside range (excluding boundaries)
- Expected: Should return true
- Result: Passed

Significance: Tests the normal, non-boundary case (Equivalence Class 3)

2. Test Case: testContains_ValueAtLowerBound_ReturnsTrue
- Method: contains(double value)
- Partition: Value at lower boundary (inclusive)
- Expected: Should return true
- Result: Passed

Significance: Tests lower boundary inclusion (Equivalence Class 2, Boundary Value Analysis)

3. Test Case: testContains_ValueAtUpperBound_ReturnsTrue
- Method: contains(double value)
- Partition: Value at upper boundary (inclusive)
- Expected: Should return true
- Result: Passed
- Significance: Tests upper boundary inclusion (Equivalence Class 4, Boundary Value Analysis)

4. Test Case: testContains_ValueBelowLowerBound_ReturnsFalse
- Method: contains(double value)
- Partition: Value below lower bound
- Expected: Should return false
- Result: Passed

Significance: Tests values below the valid range (Equivalence Class 1)

5. Test Case: testContains_ValueAboveUpperBound_ReturnsFalse
- Method: contains(double value)
- Partition: Value above upper bound
- Expected: Should return false
- Result: Passed

Significance: Tests values above the valid range (Equivalence Class 5)
## 3. getLowerBound(): double
- Test Case: getLowerBound_ShouldReturnCorrectValue()
- method: getlowerBoud()
- Partition: lower bound value
- Expected: -1
- Result: PASSED
## 4. getUpperBound(): double
- Test Case: getUpperBound_ShouldReturnCorrectValue()
- method: getUpperBound()
- Partition: upper bound value
- Expected: 1
- Result: DID NOT PASS
## 5.getLength(): double
- Test Case: getLength_ShouldReturnCorrectValue()
- method: getLength()
- partition: upper bound - lower bound
- Expected: 2
- Result: PASSED

# DataUtilitiesTest.java
## 1. calculateColumnTotal(Values2D data, int column)

### Test Case: testCalculateColumnTotal_TwoRows_ShouldSumValues

- Method: calculateColumnTotal(Values2D, int)
- Partition: Valid data object, valid column index, multiple rows
- Input:
  - Row count = 2
  - Values: (0,0) = 7.5, (1,0) = 2.5
- Expected Output: 10.0
- Result: Passed

Significance:
Tests the normal equivalence class where all inputs are valid and values are summed correctly.

---

### Test Case: testCalculateColumnTotal_NullData_ShouldThrowException

- Method: calculateColumnTotal(Values2D, int)
- Partition: Null input
- Input: data = null
- Expected Output: IllegalArgumentException
- Result: Passed

Significance:
Tests invalid input partition and verifies proper exception handling.

---

## 2. calculateColumnTotal(Values2D data, int column, int[] validRows)

### Test Case: testCalculateColumnTotal_WithValidRows_ShouldSumSelectedRows

- Method: calculateColumnTotal(Values2D, int, int[])
- Partition: Valid data, valid column, subset of rows
- Input:
  - Row count = 3
  - validRows = {0, 2}
  - Values: (0,0) = 4.0, (2,0) = 6.0
- Expected Output: 10.0
- Result: Passed

Significance:
Tests correct summation when only selected rows are considered.

---

## 3. calculateRowTotal(Values2D data, int row)

### Test Case: testCalculateRowTotal_TwoColumns_ShouldSumValues

- Method: calculateRowTotal(Values2D, int)
- Partition: Valid row index, multiple columns
- Input:
  - Column count = 2
  - Values: (0,0) = 3.0, (0,1) = 4.0
- Expected Output: 7.0
- Result: Passed

Significance:
Tests the normal case where values across columns in a valid row are summed correctly.

---

## 4. createNumberArray(double[] data)

### Test Case: testCreateNumberArray_ValidInput_ShouldReturnNumberArray

- Method: createNumberArray(double[])
- Partition: Valid numeric array
- Input: {1.0, 2.5, 3.0}
- Expected Output: Number[] {1.0, 2.5, 3.0}
- Result: Passed

Significance:
Tests the normal equivalence class for valid numeric input.

---

### Test Case: testCreateNumberArray_NullInput_ShouldThrowException

- Method: createNumberArray(double[])
- Partition: Null input
- Input: null
- Expected Output: IllegalArgumentException
- Result: Passed

Significance:
Tests boundary case for invalid (null) input.

---

## 5. createNumberArray2D(double[][] data)

### Test Case: testCreateNumberArray2D_Valid2DInput_ShouldReturn2DNumberArray

- Method: createNumberArray2D(double[][])
- Partition: Valid two-dimensional numeric array
- Input:
  {{1.0, 2.0},
   {3.0, 4.0}}
- Expected Output: Number[][] equivalent of input
- Result: Passed

Significance:
Tests correct conversion of a valid 2D array into a 2D Number array.

# 4 How the team work/effort was divided and managed

Text…

# 5 Difficulties encountered, challenges overcome, and lessons learned

Text…

# 6 Comments/feedback on the lab itself

Text…
>>>>>>> 08ac0d685495df5fff25f231d59ee343f132e132:seng438-a2-team_number.md
