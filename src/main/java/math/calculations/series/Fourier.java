package math.calculations.series;

public class Fourier implements Series {
    private final FourierCore core;
    private final int N;


    public Fourier(FourierCore core, int N) {
        this.core = core;
        this.N = N / 2;
    }

    public int amount() {
        return N;
    }

    public double[] transform(double[] discreteFunction, double a, double b) {
        int length = discreteFunction.length;
        double dx = (b - a) / (double) length;
        double[] coefficients = new double[N];
        double x;

        for (int coefficientIndex = 0; coefficientIndex < N; coefficientIndex++) {
            for (int lengthIndex = 0; lengthIndex < length; lengthIndex++) {
                x = dx * lengthIndex;
                coefficients[coefficientIndex] += dx * discreteFunction[lengthIndex] * core.directMethod(x, coefficientIndex, b);
            }
            coefficients[coefficientIndex] = coefficients[coefficientIndex] * (2 / b);
//            coefficients[coefficientIndex] = coefficients[coefficientIndex] * b;
        }
        return coefficients;
    }

    public double[] inverseTransform(double[] coefficients, int length, double dx) {
        double[] function = new double[length];
        double b = dx * (double) length;
        double x;

        for (int lengthIndex = 0; lengthIndex < length; lengthIndex++) {
            function[lengthIndex] = coefficients[0] / 2;
            x = dx * lengthIndex;
            for (int coefficientIndex = 1; coefficientIndex < N; coefficientIndex++) {
                function[lengthIndex] += coefficients[coefficientIndex] * core.inverseMethod(x, coefficientIndex, b);
            }
        }
        return function;
    }
}
