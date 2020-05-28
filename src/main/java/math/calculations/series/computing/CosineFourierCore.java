package math.calculations.series.computing;

import math.calculations.series.FourierCore;

import static java.lang.Math.*;

public class CosineFourierCore implements FourierCore {
    @Override
    public double directMethod(double x, double... a) {
        return cos((PI * x * a[0]) / a[1]);
    }

    @Override
    public double inverseMethod(double x, double... a) {
        return directMethod(x, a);
    }
}
