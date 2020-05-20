package problem;

import math.Function;
import math.Integral;
import math.LerpMethodIntegral;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Math.*;

public class IntegralTest {

    private static final double DELTA = 1E-8d;

    @Test
    void testIntegral1() {
        Function function = (x, a) -> x * sin(x + a[0]);
        Integral integral = new LerpMethodIntegral(function);

        Assertions.assertEquals(0.5d, integral.calculate(-200, 200, 2), DELTA);
    }

    @Test
    void testBrusselator1() {
        double l = 1;
        double A = 1;
        double B = 1;

        Function u1 = (x, a) -> x;
        Function u2 = (x, a) -> x;

        /* Differential equation system */
        Function f1 = (x, a) -> A - (B + 1) * u1.value(x, a[0]) + u1.value(x, a[0]) * u1.value(x, a[0]) + u2.value(x, a[0]);
        Function f2 = (x, a) -> B * u1.value(x, a[0]) - u1.value(x, a[0]) * u1.value(x, a[0]) + u2.value(x, a[0]);

        /*
        Whole system in one function F.value(x, k, i),
        where k - layer, i - number of equation
         */
        Function F  = (x, a) -> a[1] == 0 ? f1.value(x, a[0]) : f2.value(x, a[0]);

        /* Fourier transform */
        Function z  = (x, a) -> cos(a[0] * PI * x / l);
        Integral integral = new LerpMethodIntegral((x, a) -> F.value(x, a[0], a[1]) * z.value(x, a[2]));
        Function fi = (x, a) -> 2 / l * integral.calculate(0, l, a[0], a[1], a[2]);
//        java.util.function.Function<Double[], Double[]> fourierTransform = (x) -> {
//            for (int i = )
//        }

        Assertions.assertEquals(0.5d, integral.calculate(-2, 2), DELTA);
    }

    @Test
    void testBrusselator2() {

    }
}
