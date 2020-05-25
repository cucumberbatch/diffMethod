package math.calculations.series;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class Fourier implements Series {
    public static int N = 100;

    public static double[] transform(double[] f, double a, double b) {
        int length = f.length;
        double dx = (b - a) / length;
        double[] c = new double[N];
        for (int n = 0; n < N; n++) {
            c[n] = 0;
            for (int i = 0; i < length; i++) {
                c[n] += f[i] * cos((PI * n * dx * i) / b) * dx;
            }
            c[n] = 2 / b * c[n];
        }
        return c;
    }

    public static double[] transform(double[] f, double[] x) {
        int length = f.length;
        double dx = x[1] - x[0];
        double[] c = new double[N];
        for (int n = 0; n < N; n++) {
            c[n] = 0;
            for (int i = 0; i < length; i++) {
                c[n] += f[i] * cos((PI * n * dx * i) / x[x.length - 1]) * dx;
            }
            c[n] = 2 / x[x.length - 1] * c[n];
        }
        return c;
    }

    public static double[] inverseTransform(double[] c, double[] x) {
        int length = x.length;
        double[] f = new double[length];
        for (int ix = 0; ix < length; ix++) {
            f[ix] = c[0] / 2;
            for (int i = 1; i < N; i++) {
                f[ix] += c[i] * cos(PI * x[ix] * i / x[length - 1]);
            }
        }
        return f;
    }

    public static double[] inverseTransform(double[] c, double dx) {
        int length = c.length;
        double[] f = new double[length];
        for (int ix = 0; ix < length; ix++) {
            f[ix] = c[0] / 2;
            for (int i = 1; i < N; i++) {
                f[ix] += c[i] * cos(PI * dx * ix * i / (dx * (length - 1)));
            }
        }
        return f;
    }

}
