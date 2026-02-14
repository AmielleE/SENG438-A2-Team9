package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RangeLengthTest {

```
private Range exampleRange;

@BeforeEach
void setUp() {
    exampleRange = new Range(-1, 1);
}

@Test
void getLength_ShouldReturnCorrectValue() {
    assertEquals(2.0, exampleRange.getLength(), 1e-9,
            "Length of range [-1,1] should be 2");
}
```

}
