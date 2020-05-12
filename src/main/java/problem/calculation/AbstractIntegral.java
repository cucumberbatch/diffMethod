package problem.calculation;

import problem.calculation.impl.Integral;

public abstract class AbstractIntegral implements Integral {
    public abstract double function(double x);

    @Override
    public double calculate(double a, double b) {
        double h = 0.0001d;
        double current = a;
        double sum = 0;
        while (current <= b) {
            sum += h * (function(current) + function(current + h));
            current += h;
        }
        return sum / 2;
    }
}
