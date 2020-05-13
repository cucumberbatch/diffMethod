package math;

public class LerpMethodIntegral extends AbstractIntegral {
    public LerpMethodIntegral(Function function) {
        super(function);
    }

    @Override
    public double calculate(double a, double b, double... c) {
        double h = 0.0001d;
        double current = a;
        double sum = 0;
        while (current <= b - h / 2) {
            sum += h * (function.value(current, c) + function.value(current + h, c));
            current += h;
        }
        return sum / 2;
    }
}
