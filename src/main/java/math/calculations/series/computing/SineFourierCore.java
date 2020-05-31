package math.calculations.series.computing;

import math.calculations.series.FourierCore;

import static java.lang.Math.*;

public class SineFourierCore implements FourierCore {
    @Override
    public double directMethod(double x, double... a) {
        return sin((PI * x * a[0]) / a[1]);
    }

    @Override
    public double inverseMethod(double x, double... a) {
        return directMethod(x, a);
    }
}
