package math.calculations.series;

/**
 * A computing core interface for Fourier transform
 */
public interface FourierCore {

    double directMethod(double x, double... a);

    double inverseMethod(double x, double... a);
}
