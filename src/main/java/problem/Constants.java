package problem;

public class Constants {

	// Parameters of grid:
	public static final double Length	= 1.0d;		// length or depth of sample
	public static final double Time		= 1.0d;		// total time of experiment
	public static final int N			= 10;		// amount of steps for length fracture
	public static final int M			= 10;		// amount of steps for time fracture

	// Simulation parameters
	public static final double U0 		= 5.0d;
	public static final double lambda 	= 1.0d;
	public static final double rho 		= 1.0d;
	public static final double c		= 1.0d;
	public static final double alpha 	= 1.0d / 2.0d;
	public static final double a		= Math.sqrt(lambda / (rho * c));

}
