package problem.utils;

public class Differential {

    static double firstOrder(double next, double current, double step) {
        return (next - current) / step;
    }

    static double secondOrder(double next, double current, double previous, double step) {
        return (previous - 2 * current + next) / step / step;
    }

}
