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

This lab focused on developing a unit test suite using a requirements-based black-box approach. We designed JUnit 5 tests for selected methods in org.jfree.data.Range and org.jfree.data.DataUtilities, using the Javadoc specifications as our source of requirements rather than the implementation itself.

To systematically design our tests, we applied Equivalence Class Partitioning (ECP) and Boundary Value Analysis (BVA) to cover normal behavior, edge cases, and invalid inputs. For methods that depended on the Values2D interface, we used Mockito to mock inputs and isolate the unit under test. The resulting test suite was then executed to identify both correct behavior and injected defects.

# 2 Detailed description of unit test strategy

Our team used a requirements-based (black-box) unit testing strategy. We designed tests strictly from the Javadoc specifications for org.jfree.data.Range and org.jfree.data.DataUtilities. We did not use the implementation code to decide expected results. Instead, we treated the Javadoc as the requirements and built tests to check whether the methods behave as specified.

To keep our approach consistent across methods, we followed the same steps:

1. Identify input domains for each parameter (e.g., real numbers for bounds/values, nullable objects, array inputs, row/column indexes).

2. Split each input domain into equivalence classes:

   - Valid equivalence classes (inputs that should work normally)

   - Invalid equivalence classes (inputs that should return false, clamp, return null, or throw an exception depending on the spec)

3. Apply Boundary Value Analysis (BVA) whenever boundaries matter (e.g., exact lower bound, exact upper bound, zero margins, and “just outside” conditions).

4. Create at least one test per partition and clearly name tests so the purpose is obvious.


Below are the main partitions we designed. Section 3 lists the exact test cases we implemented for each method and shows which partition each one covers.

# Range class – partitioning strategy
  
  ### getCentralValue()

Input domain: lowerBound and upperBound are real numbers, with lowerBound ≤ upperBound.
Partitions (equivalence classes):

- Typical valid range (e.g., symmetric range like [-1, 1])

- (Possible edge class) zero-length range where lowerBound = upperBound

- Negative-only and positive-only ranges (general numeric partitions)

Covered by: TC_GETCENTRALVALUE_01 (normal symmetric range partition)


 ### contains(double value)

Input domain: value is any real number.
Equivalence classes:

- EC1: value < lowerBound

- EC2: value = lowerBound (boundary)

- EC3: lowerBound < value < upperBound

- EC4: value = upperBound (boundary)

- EC5: value > upperBound

Boundary Value Analysis used: we tested values at both bounds and outside both bounds because inclusive boundary behavior is a common defect area.

Covered by:

- testContains_ValueBelowLowerBound_ReturnsFalse (EC1)

- testContains_ValueAtLowerBound_ReturnsTrue (EC2, BVA)

- testContains_ValueInsideRange_ReturnsTrue (EC3)

- testContains_ValueAtUpperBound_ReturnsTrue (EC4, BVA)

- testContains_ValueAboveUpperBound_ReturnsFalse (EC5)


### getLowerBound() / getUpperBound() / getLength()

These methods are simple, but we still treated them as black-box checks based on Javadoc meaning:

- getLowerBound() should return the stored lower bound

- getUpperBound() should return the stored upper bound

- getLength() should return (upper − lower)

Covered by:

- getLowerBound_ShouldReturnCorrectValue()

- getUpperBound_ShouldReturnCorrectValue()

- getLength_ShouldReturnCorrectValue()

### combine(Range range1, Range range2)

Input domain: range1 and range2 may be valid Range objects or null.

Equivalence classes:

- EC1: range1 and range2 are both non-null

- EC2: range1 is null, range2 non-null

- EC3: range1 non-null, range2 is null

- EC4: both are null (boundary/robustness)

Covered by:

- combine_TwoNonNullRanges_ShouldReturnMinLowerMaxUpper (EC1)

- combine_FirstNull_ShouldReturnSecond (EC2)

- combine_SecondNull_ShouldReturnFirst (EC3)

- combine_BothNull_ShouldReturnNull (EC4)


### constrain(double value)

Input domain: value is any real number.

Equivalence classes:

- EC1: value inside range → returned unchanged

- EC2: value below lowerBound → clamp to lowerBound

- EC3: value above upperBound → clamp to upperBound

BVA focus: outside-bounds values + expected clamping behavior.

Covered by:

- constrain_ValueInsideRange_ShouldReturnSameValue (EC1)

- constrain_ValueBelowLower_ShouldReturnLowerBound (EC2, BVA clamp)

- constrain_ValueAboveUpper_ShouldReturnUpperBound (EC3, BVA clamp)


### equals(Object obj)

Input domain: obj can be Range, null, or another type.
Equivalence classes:

- EC1: Same bounds → true

- EC2: Different bounds → false

- EC3: null or different type → false (robustness)

Covered by:

- equals_SameBounds_ShouldReturnTrue (EC1)

- equals_DifferentBounds_ShouldReturnFalse (EC2)

- equals_NullOrDifferentType_ShouldReturnFalse (EC3)

 ### expand(Range range, double lowerMargin, double upperMargin)

Input domain: range can be Range or null; margins are doubles (expected to be ≥ 0 per spec/assumptions).

Equivalence classes:

- EC1: valid range with positive margins

- EC2: valid range with zero margins (boundary)

- EC3: null range (invalid/exception)

Covered by:

- expand_PositiveMargins_ShouldExpandBothSides (EC1)

- expand_ZeroMargins_ShouldReturnSameBounds (EC2, BVA)

- expand_NullRange_ShouldThrowInvalidParameterException (EC3)

 ### expandToInclude(Range range, double value)

Input domain: range may be null; value is any real number.

Equivalence classes:

- EC1: range is null → create [value, value]

- EC2: value inside range → unchanged

- EC3: value below lowerBound → expand lower

- EC4: value above upperBound → expand upper

Covered by: the four expandToInclude_... test cases listed in Section 3.

 ### intersects(double lower, double upper)

Input domain: lower and upper are real numbers.

Equivalence classes:

- EC1: interval completely left of range

- EC2: interval completely right of range

- EC3: partial overlap

- EC4: interval fully contained

- EC5: invalid interval (lower > upper) (robustness)

Covered by: the five intersects_... test cases listed in Section 3.

 ### shift(...) and toString()

For shift:

We partitioned based on allowZeroCrossing = true vs false, and used a normal delta shift case.
For toString:

We checked that both bounds appear in the string output (black-box “contains expected information” check).

Covered by:

- shift_TwoArg_ShouldMoveBothBoundsByDelta

- shift_AllowZeroCrossingTrue_ShouldCrossZeroNormally

- shift_AllowZeroCrossingFalse_ShouldNotCrossZero

- toString_ShouldContainLowerAndUpperBounds

 ### DataUtilities class – partitioning strategy (with mocking)

Several DataUtilities methods depend on the Values2D interface. Since Values2D is an interface (not a concrete class we can easily instantiate with real data), we used Mockito to mock it. This allowed us to control the row/column counts and returned values precisely, so each test targets a specific partition.

### calculateColumnTotal(Values2D data, int column)

Equivalence classes:

- EC1: valid data + valid column + multiple rows (normal)

- EC2: data = null (invalid input, should throw per requirement)

Covered by:

- testCalculateColumnTotal_TwoRows_ShouldSumValues (EC1)

- testCalculateColumnTotal_NullData_ShouldThrowException (EC2)

 ### calculateColumnTotal(Values2D data, int column, int[] validRows)

Equivalence classes:

- EC1: validRows selects a subset of rows (normal subset-sum behavior)

Covered by:

- testCalculateColumnTotal_WithValidRows_ShouldSumSelectedRows

### calculateRowTotal(Values2D data, int row)

Equivalence classes:

- EC1: valid row + multiple columns (normal)

Covered by:

- testCalculateRowTotal_TwoColumns_ShouldSumValues

### createNumberArray(double[] data) and createNumberArray2D(double[][] data)

Equivalence classes:

- EC1: valid array input → correct conversion output

- EC2: null input → should throw (invalid)

Covered by:

- testCreateNumberArray_ValidInput_ShouldReturnNumberArray (EC1)

- testCreateNumberArray_NullInput_ShouldThrowException (EC2)

- testCreateNumberArray2D_Valid2DInput_ShouldReturn2DNumberArray (EC1)

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

## 11. hashCode(): int

1. Test Case: hashCode_EqualObjects_ShouldHaveSameHashCode
-Method: hashCode()
-Partition: Equal objects (same bounds)
-Input: Range(0, 1) and Range(0, 1)
-Expected: Same hash code for equivelant objects
-Result: DID NOT PASS

Significance: Tests hashCode and equals contract requirement partition.

## 12. intersects(double lower, double upper): boolean

1. Test Case: intersects_DisjointLeft_ShouldReturnFalse
-Method: intersects(double lower, double upper)
-Partition: Interval completely left of range
-Input: range = [0, 10], test interval = [-5, -1]
-Expected: false
-Result: DID NOT PASS

Significance: Tests disjoint left equivalence class.

2. Test Case: intersects_DisjointRight_ShouldReturnFalse
-Method: intersects(double lower, double upper)
-Partition: Interval completely right of range
-Input: range = [0, 10], test interval = [11, 20]
-Expected: false
-Result: Passed

Significance: Tests disjoint right equivalence class.

3. Test Case: intersects_Overlapping_ShouldReturnTrue
-Method: intersects(double lower, double upper)
-Partition: Partially overlapping interval
-Input: range = [0, 10], test interval = [5, 15]
-Expected: true
-Result: DID NOT PASS

Significance: Tests overlap equivalence class.

4. Test Case: intersects_ContainedInterval_ShouldReturnTrue
-Method: intersects(double lower, double upper)
-Partition: Interval fully contained within range
-Input: range = [0, 10], test interval = [2, 3]
-Expected: true
-Result: Passed

Significance: Tests contained interval equivalence class.

5. Test Case: intersects_InvalidIntervalLowerGreaterThanUpper_ShouldReturnFalse
-Method: intersects(double lower, double upper)
-Partition: Invalid interval (lower > upper)
-Input: range = [0, 10], test interval = [5, 4]
-Expected: false
-Result: Passed

Significance: Tests invalid input robustness partition.

## 13. shift(Range base, double delta): Range

1. Test Case: shift_TwoArg_ShouldMoveBothBoundsByDelta
-Method: shift(Range base, double delta)
-Partition: Normal shift with positive delta
-Input: base = [1, 3], delta = 2
-Expected: [3, 5]
-Result: DID NOT PASS

Significance: Tests normal equivalence class for shifting both bounds.

## 14. shift(Range base, double delta, boolean allowZeroCrossing): Range

1. Test Case: shift_AllowZeroCrossingTrue_ShouldCrossZeroNormally
-Method: shift(Range base, double delta, boolean allowZeroCrossing)
-Partition: allowZeroCrossing = true
-Input: base = [-1, 1], delta = 2
-Expected: [1, 3]
-Result: DID NOT PASS

Significance: Tests equivalence class where crossing zero is allowed.

2. Test Case: shift_AllowZeroCrossingFalse_ShouldNotCrossZero
-Method: shift(Range base, double delta, boolean allowZeroCrossing)
-Partition: allowZeroCrossing = false (zero crossing constraint)
-Input: base = [-1, 1], delta = 2
-Expected: Lower bound should not be negative after shift
-Result: DID NOT PASS

Significance: Tests boundary and constraint partition when zero crossing is disallowed.

## 15. toString(): String

1. Test Case: toString_ShouldContainLowerAndUpperBounds
-Method: toString()
-Partition: Normal string representation
-Input: range = [-1, 1]
-Expected: Output should contain -1 and 1
-Result: DID NOT PASS

Significance: Tests that string representation includes both bounds.


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




Mocking allows us to isolate the unit under test and focus strictly on its internal logic without depending on external implementations. It gives us full control over return values, which makes testing boundary conditions and edge cases much easier. Using mocks also makes tests more predictable and repeatable since they are not affected by outside behavior. This was especially helpful when testing methods that depend on interfaces like Values2D. However, mocked objects are not real implementations, so they may reduce realism in testing. Tests can also become tightly coupled to internal method calls, making them fragile if the implementation changes. Overall, mocking improves isolation and control, but it may hide integration level defects.

# 4 How the team work/effort was divided and managed

Pair 1: Fatma & Amielle
Fatma & Amielle focused on the Range class, where they designed equivalence classes and boundary value partitions and implemented tests for methods such as contains(), combine(), constrain(), expand(), expandToInclude(), and equals(), with emphasis on boundary analysis, robustness testing (including null inputs), and identifying defects through failing tests.

Pair 2: Josral, Faris & Erioluwa
Josral, Faris & Erioluwa focused on the DataUtilities class, where they designed and implemented tests using Mockito for methods involving Values2D, including calculateColumnTotal() (both overloads), calculateRowTotal(), createNumberArray(), and createNumberArray2D(), ensuring coverage of normal, boundary, and exception partitions with consistent naming and structure.
The report was created by all 5 members of the group, with each person taking one section to complete, and Josral being the only one to complete more than one.

Josral, Faris & Erioluwa sequentially checked through and verified each test made by Fatma & Amielle with vice versa also being being the case with the tests created by Josral, Faris & Erioluwa.


# 5 Difficulties encountered, challenges overcome, and lessons learned 

One difficulty was interpreting the Javadoc as precise requirements, especially for edge cases like null inputs and boundary behavior. Setting up Mockito correctly for Values2D also required careful configuration and debugging. We overcame this by clearly defining input domains and equivalence classes before writing code. Some failing tests helped us identify real defects in the class rather than errors in our tests. Overall, we learned the importance of strict requirement based testing and proper test planning.

# 6 Comments/feedback on the lab itself

This lab was very effective in reinforcing requirement-based black-box testing. Using Javadoc as the source of requirements forced us to carefully read specifications and translate them into equivalence classes, boundary values, and robustness tests instead of relying on the actual implementation. It also helped us understand the importance of writing tests based strictly on expected behavior, especially since some of our tests intentionally failed due to injected defects. The integration of JUnit 5 and Mockito made the experience more realistic and aligned with industry practices, particularly when testing methods that depend on interfaces.
One challenge was that some Javadoc descriptions were brief or slightly ambiguous, which required interpretation and discussion within the team. Providing one fully worked example of partition derivation in the assignment instructions could help clarify expectations. Additionally, clearer guidance on the expected depth of testing (e.g., worst case combinations for multiparameter methods) would improve alignment across groups. Overall, the lab was well structured and valuable for building practical unit testing skills.
