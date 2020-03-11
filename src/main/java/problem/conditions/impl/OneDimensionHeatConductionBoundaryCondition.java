package problem.conditions.impl;

import problem.conditions.BoundaryCondition;
import problem.utils.FieldConfiguration;
import problem.Constants;

public class OneDimensionHeatConductionBoundaryCondition implements BoundaryCondition {

	@Override
	public double u(double x, double t) {

		if (t == 0) {
			// u(x, 0) = 0
			return 1.0d;
		}
		else if (x == 0) {
			// u(0, t) = 0
			return Constants.U0;
		}

		// u(x, t) = 0
		return 0.0d;
	}

	@Override
	public void apply(FieldConfiguration configuration) {
		for (int n = 0; n < configuration.N; n++) {
			for (int m = 0; m < configuration.M; m++) {
				configuration.matrix.value(n, m, u(
						(double) n * configuration.lengthStep,
						(double) m * configuration.timeStep
					)
				);
			}
		}
	}

}
