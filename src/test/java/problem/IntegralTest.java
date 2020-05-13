package problem;

import math.Function;
import math.RectangleMethodIntegral;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import problem.calculation.impl.Integral;

public class IntegralTest {

    private static final double DELTA = 1E-8d;

    @Test
    void testIntegral1() {
        Function function = (x) -> x;
        Integral integral = new RectangleMethodIntegral(function);

        Assertions.assertEquals(0.0d, integral.calculate(0, 1), DELTA);
    }
}
