package problem.conditions;

import problem.utils.FieldConfiguration;

public interface BoundaryCondition {
    double u(double x, double t);

    default void apply(FieldConfiguration configuration) {
        for (int n = 0; n < configuration.n; n++) {
            for (int m = 0; m < configuration.m; m++) {
                configuration.matrix.value(n, m, u(
                        (double) n * configuration.lengthStep,
                        (double) m * configuration.timeStep
                        )
                );
            }
        }
    }
}
