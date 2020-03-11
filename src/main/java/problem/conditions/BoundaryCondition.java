package problem.conditions;

import problem.utils.FieldConfiguration;

public interface BoundaryCondition {
	double u(double x, double t);
	void apply(FieldConfiguration configuration);
}
