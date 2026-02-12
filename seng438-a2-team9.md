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


## 6. combine(Range range1, Range range2): Range

1. Test Case: combine_TwoNonNullRanges_ShouldReturnMinLowerMaxUpper
-Method: combine(Range, Range)
-Partition: Two valid non null overlapping ranges
-Input: range1 = [0,5], range2 = [3,10]
-Expected: Combined range should be [0,10]
-Result: DID NOT PASS

Significance: Tests normal valid input partition; reveals defect (exception/incorrect bounds) for valid overlapping ranges.

2. Test Case: combine_FirstNull_ShouldReturnSecond
-Method: combine(Range, Range)
-Partition: First range is null
-Input: range1 = null, range2 = [1,2]
-Expected: Should return [1,2]
-Result: DID NOT PASS

3.Significance: Tests null handling partition for first parameter.

Test Case: combine_SecondNull_ShouldReturnFirst
-Method: combine(Range, Range)
-Partition: Second range is null
-Input: range1 = [-2,4], range2 = null
-Expected: Should return [-2,4]
-Result: DID NOT PASS

Significance: Tests null handling partition for second parameter.

4.Test Case: combine_BothNull_ShouldReturnNull
-Method: combine(Range, Range)
-Partition: Both inputs null
-Input: range1 = null, range2 = null
-Expected: Should return null
-Result: PASSED

Significance: Tests boundary case when both parameters are null.

## 7. constrain(double value): double

1. Test Case: constrain_ValueInsideRange_ShouldReturnSameValue  
- Method: constrain(double value)  
- Partition: Value inside range  
- Input: Range[-1, 1], value = 0.25  
- Expected: 0.25  
- Result: Passed  

Significance: Tests inside range equivalence class (value unchanged).

2. Test Case: constrain_ValueBelowLower_ShouldReturnLowerBound  
- Method: constrain(double value)  
- Partition: Value below lower bound  
- Input: Range[-1, 1], value = -5  
- Expected: -1  
- Result: DID NOT PASS  

Significance: Tests below range partition and boundary clamping to lower bound.

3. Test Case: constrain_ValueAboveUpper_ShouldReturnUpperBound  
- Method: constrain(double value)  
- Partition: Value above upper bound  
- Input: Range[-1, 1], value = 5  
- Expected: 1  
- Result: Passed  

Significance: Tests above range partition and boundary clamping to upper bound.

## 8. equals(Object obj): boolean

1. Test Case: equals_SameBounds_ShouldReturnTrue  
- Method: equals(Object obj)  
- Partition: Two Range objects with same bounds  
- Input: Range(0, 1) compared with Range(0, 1)  
- Expected: true  
- Result: Passed  

Significance: Tests equality equivalence class.

2. Test Case: equals_DifferentBounds_ShouldReturnFalse  
- Method: equals(Object obj)  
- Partition: Two Range objects with different bounds  
- Input: Range(0, 1) compared with Range(0, 2)  
- Expected: false  
- Result: DID NOT PASS  

Significance: Tests inequality equivalence class.

3. Test Case: equals_NullOrDifferentType_ShouldReturnFalse  
- Method: equals(Object obj)  
- Partition: Null object / different type  
- Input: null and not a range  
- Expected: false  
- Result: Passed  

Significance: Tests robustness partition for invalid comparisons.

## 9. expand(Range range, double lowerMargin, double upperMargin): Range

1. Test Case: expand_PositiveMargins_ShouldExpandBothSides  
- Method: expand(Range range, double lowerMargin, double upperMargin)  
- Partition: Valid range with positive margins  
- Input: base = [0, 10], lowerMargin = 0.1, upperMargin = 0.2  
- Expected: [-1, 12]  
- Result: DID NOT PASS  

Significance: Tests margin based expansion using normal valid partitions.

2. Test Case: expand_ZeroMargins_ShouldReturnSameBounds  
- Method: expand(Range range, double lowerMargin, double upperMargin)  
- Partition: Zero margins (boundary case)  
- Input: base = [0, 10], lowerMargin = 0.0, upperMargin = 0.0  
- Expected: [0, 10]  
- Result: DID NOT PASS  

Significance: Tests boundary case where margins shouldnt cause change.

3. Test Case: expand_NullRange_ShouldThrowInvalidParameterException
- Method: expand(Range range, double lowerMargin, double upperMargin)
- Partition: Null range object
- Input: range = null, lowerMargin = 0.1, upperMargin = 0.2
- Expected: InvalidParameterException
- Result: DID NOT PASS

Significance: Tests exception handling partition for null range input.

## 10. expandToInclude(Range range, double value): Range

1. Test Case: expandToInclude_NullRange_ShouldCreateSinglePointRange  
- Method: expandToInclude(Range range, double value)  
- Partition: Null range input  
- Input: range = null, value = 5  
- Expected: [5, 5]  
- Result: Passed  

Significance: Tests null handling partition and creation behavior.

2. Test Case: expandToInclude_ValueInside_ShouldReturnUnchanged  
- Method: expandToInclude(Range range, double value)  
- Partition: Value inside existing range  
- Input: base = [0, 10], value = 5  
- Expected: [0, 10]  
- Result: DID NOT PASS  

Significance: Tests inside range equivalence class.

3. Test Case: expandToInclude_ValueBelowLower_ShouldExpandLower  
- Method: expandToInclude(Range range, double value)  
- Partition: Value below lower bound  
- Input: base = [0, 10], value = -2  
- Expected: [-2, 10]  
- Result: DID NOT PASS  

Significance: Tests lower bound expansion partition.

4. Test Case: expandToInclude_ValueAboveUpper_ShouldExpandUpper  
- Method: expandToInclude(Range range, double value)  
- Partition: Value above upper bound  
- Input: base = [0, 10], value = 12  
- Expected: [0, 12]  
- Result: DID NOT PASS  

Significance: Tests upper bound expansion partition.





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

One major difficulty was ensuring that all partitions and strategy components interacted correctly without unintended side effects. Some unit tests initially passed in isolation but failed during integration due to incorrect assumptions in the mocked behavior.
Another challenge involved designing effective test cases that covered all logical branches. Edge cases, especially around boundary conditions, required careful thought and refinement.
We also encountered minor version control conflicts when merging branches, which highlighted the importance of frequent commits and communication.

# 6 Comments/feedback on the lab itself

The lab was effective in reinforcing concepts related to modular design, partitioning strategies, and unit testing. It provided practical exposure to mocking and highlighted the difference between isolated testing and full integration testing.
One improvement could be clearer examples demonstrating how different partitions relate to strategy design before implementation begins. Additionally, providing a small reference example of effective mocking patterns could reduce confusion for teams unfamiliar with mocking frameworks.
Overall, the lab was valuable in strengthening both technical skills and teamwork coordination, and it helped bridge theoretical concepts with practical implementation.

