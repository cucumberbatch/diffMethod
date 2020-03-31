package problem;

public class Constants {

    // Parameters of grid:
    public static final double Length	= 2.0d;     // length or depth of sample
    public static final double Time		= 4.0d;     // total time of experiment
    public static final int N			= 250;       // amount of steps for length fracture
    public static final int M			= 500;       // amount of steps for time fracture

    // Simulation parameters
    public static final double U0 		= 5.0d;
    public static final double lambda 	= 1.0d;
    public static final double rho 		= 1.0d;
    public static final double c		= 1.0d;
    public static final double alpha 	= 1.0d / 2.0d;
    public static final double a_sqr	= lambda / (rho * c);
    public static final double p0		= 84;
    public static final double p		= p0 / (rho * c);
    public static final double b0		= 4 * a_sqr * (3 - alpha) / (1 - alpha) / (1 - alpha);
    public static final double b1		= 2 * Math.pow(U0, alpha - 1) * p * (3 - alpha) / (1 + alpha);
    public static final double L		= Math.sqrt(b0 / b1);

    // Output data folder destination
    public static final String DATA_FOLDER = "D:\\User\\Documents\\_collected\\";

    // Output format for data in console
    public static final String CONSOLE_OUTPUT_FORMAT = "#0.00";
}
