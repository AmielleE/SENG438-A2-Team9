package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RangeCentralValueTest {

```
private Range exampleRange;

@BeforeEach
void setUp() {
    exampleRange = new Range(-1, 1);
}

@Test
void centralValueShouldBeZero() {
    assertEquals(0.0, exampleRange.getCentralValue(), 1e-9,
            "The central value of -1 and 1 should be 0");
}
```

}
