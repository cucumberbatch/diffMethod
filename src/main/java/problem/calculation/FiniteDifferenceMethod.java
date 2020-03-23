package problem.calculation;

import problem.models.Matrix;
import problem.utils.FieldConfiguration;
import problem.utils.matrix.solver.LinearEquationsSystemSolver;

public interface FiniteDifferenceMethod {
    FieldConfiguration solve(FieldConfiguration configuration, LinearEquationsSystemSolver solver);
}
