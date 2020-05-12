package problem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import problem.calculation.ConcreteIntegral;
import problem.calculation.impl.Integral;

public class IntegralTest {

    @Test
    void testIntegral1() {
        Integral integral = new ConcreteIntegral() {
            @Override
            public double function(double x) {
                return x;
            }
        };

        Assertions.assertEquals(0.5d, integral.calculate(0, 1));
    }
}
