package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RangeUpperBoundTest {

```
private Range exampleRange;

@BeforeEach
void setUp() {
    exampleRange = new Range(-1, 1);
}

@Test
void getUpperBound_ShouldReturnCorrectValue() {
    assertEquals(1.0, exampleRange.getUpperBound(), 1e-9,
            "Upper bound of [-1,1] should be 1");
}
```

}
