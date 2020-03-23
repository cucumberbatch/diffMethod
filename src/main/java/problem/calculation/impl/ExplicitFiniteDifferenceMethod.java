package problem.calculation.impl;

import problem.Constants;
import problem.calculation.FiniteDifferenceMethod;
import problem.models.Matrix;
import problem.solution.Solution;
import problem.utils.FieldConfiguration;
import problem.utils.matrix.solver.LinearEquationsSystemSolver;

public class ExplicitFiniteDifferenceMethod implements FiniteDifferenceMethod {
    @Override
    public FieldConfiguration solve(FieldConfiguration configuration, LinearEquationsSystemSolver solver) {
        Solution solution = new Solution();
        Matrix matrix = configuration.matrix;
        double gamma = 1;//Constants.a_sqr * configuration.timeStep / configuration.lengthStep / configuration.lengthStep;

        for (int j = 0; j < matrix.getM() - 1; j++) {
            for (int i = 1; i < matrix.getN() - 1; i++) {
                matrix.value(i, j + 1,
                        matrix.value(i - 1, j) * gamma +            // left bottom point
                        matrix.value(i, j) * (1 - 2 * gamma) +      // center bottom point
                        matrix.value(i + 1, j) * gamma +            // right bottom point
                        solution.u(i, j) * configuration.timeStep   // predict solution in point
                );
            }
        }

        return new FieldConfiguration(matrix, configuration.lengthStep, configuration.timeStep);
    }
}
