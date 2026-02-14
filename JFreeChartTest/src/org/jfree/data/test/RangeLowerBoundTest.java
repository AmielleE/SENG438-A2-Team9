package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RangeLowerBoundTest {

```
private Range exampleRange;

@BeforeEach
void setUp() {
    exampleRange = new Range(-1, 1);
}

@Test
void getLowerBound_ShouldReturnCorrectValue() {
    assertEquals(-1.0, exampleRange.getLowerBound(), 1e-9,
            "Lower bound of [-1,1] should be -1");
}
```

}
