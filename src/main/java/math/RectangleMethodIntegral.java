package math;

import problem.calculation.impl.Integral;

public class RectangleMethodIntegral implements Integral {
    private Function function;

    public RectangleMethodIntegral(Function function) {
        this.function = function;
    }

    @Override
    public double calculate(double a, double b) {
        double h = 0.0001d;
        double current = a;
        double sum = 0;
        while (current <= b - h / 2) {
            sum += h * (function.value(current) + function.value(current + h));
            current += h;
        }
        return sum / 2;
    }
}
