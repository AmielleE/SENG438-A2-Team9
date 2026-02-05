<<<<<<< HEAD:seng438-a2-team9.md
**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group: 9      |
|-----------------|
| Amielle El Makhzoumi           |   
| Fatma Alzubaidi              |   
| Josral Frederick               |   
| Faris Janjua                |   
| Erioluwa Olubadejo                |  

# 1 Introduction

Text…

# 2 Detailed description of unit test strategy

// including the input partitions you have designed

# 3 Test cases developed

Text…

// write down the name of the test methods and classes. Organize the based on
the source code method // they test. identify which tests cover which partitions
you have explained in the test strategy section //above

# 4 How the team work/effort was divided and managed

Text…

# 5 Difficulties encountered, challenges overcome, and lessons learned

Text…

# 6 Comments/feedback on the lab itself

Text…
=======
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
# 1.getCentralValue(): boolean
     - Test Case: TC_GETCENTRALVALUE_01
     - Method: getCentralValue()
     - Partition: Normal symmetric range [-1, 1]
     - Expected: Central value should be 0
     - Result: Passed
# 2. contains(double): boolean
1. Test Case: testContains_ValueInsideRange_ReturnsTrue
Method: contains(double value)
Partition: Value inside range (excluding boundaries)
Expected: Should return true
Result: Passed

Significance: Tests the normal, non-boundary case (Equivalence Class 3)

2. Test Case: testContains_ValueAtLowerBound_ReturnsTrue
Method: contains(double value)
Partition: Value at lower boundary (inclusive)
Expected: Should return true
Result: Passed

Significance: Tests lower boundary inclusion (Equivalence Class 2, Boundary Value Analysis)

3. Test Case: testContains_ValueAtUpperBound_ReturnsTrue
Method: contains(double value)
Partition: Value at upper boundary (inclusive)
Expected: Should return true
Result: Passed
Significance: Tests upper boundary inclusion (Equivalence Class 4, Boundary Value Analysis)

4. Test Case: testContains_ValueBelowLowerBound_ReturnsFalse
Method: contains(double value)
Partition: Value below lower bound
Expected: Should return false
Result: Passed

Significance: Tests values below the valid range (Equivalence Class 1)

5. Test Case: testContains_ValueAboveUpperBound_ReturnsFalse
Method: contains(double value)
Partition: Value above upper bound
Expected: Should return false
Result: Passed

Significance: Tests values above the valid range (Equivalence Class 5)
# 3. getLowerBound(): double
Test Case: getLowerBound_ShouldReturnCorrectValue()
method: getlowerBoud()
Partition: lower bound value
Expected: -1
Result: PASSED
# 4. getUpperBound(): double
Test Case: getUpperBound_ShouldReturnCorrectValue()
method: getUpperBound()
Partition: upper bound value
Expected: 1
Result: DID NOT PASS
# 5.getLength(): double
Test Case: getLength_ShouldReturnCorrectValue()
method: getLength()
partition: upper bound - lower bound
Expected: 2
Result: PASSED
# 4 How the team work/effort was divided and managed

Text…

# 5 Difficulties encountered, challenges overcome, and lessons learned

Text…

# 6 Comments/feedback on the lab itself

Text…
>>>>>>> 08ac0d685495df5fff25f231d59ee343f132e132:seng438-a2-team_number.md
