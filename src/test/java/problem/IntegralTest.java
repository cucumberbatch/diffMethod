package problem;

import math.Function;
import math.Integral;
import math.LerpMethodIntegral;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegralTest {

    private static final double DELTA = 1E-8d;

    @Test
    void testIntegral1() {
        Function function = (x, a) -> x;
        Integral integral = new LerpMethodIntegral(function);

        Assertions.assertEquals(0.5d, integral.calculate(0, 1), DELTA);
    }
}
