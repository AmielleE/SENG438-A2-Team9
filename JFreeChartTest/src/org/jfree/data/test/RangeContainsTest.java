package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RangeContainsTest {

```
private Range exampleRange;

@BeforeEach
void setUp() {
    exampleRange = new Range(-1, 1);
}

@Test
void containsValueInsideRange_ShouldReturnTrue() {
    assertTrue(exampleRange.contains(0),
            "0 should be inside the range [-1,1]");
}

@Test
void containsLowerBound_ShouldReturnTrue() {
    assertTrue(exampleRange.contains(-1),
            "-1 should be included in the range");
}

@Test
void containsUpperBound_ShouldReturnTrue() {
    assertTrue(exampleRange.contains(1),
            "1 should be included in the range");
}

@Test
void containsValueBelowLowerBound_ShouldReturnFalse() {
    assertFalse(exampleRange.contains(-2),
            "-2 should not be inside the range");
}

@Test
void containsValueAboveUpperBound_ShouldReturnFalse() {
    assertFalse(exampleRange.contains(2),
            "2 should not be inside the range");
}
```

}
