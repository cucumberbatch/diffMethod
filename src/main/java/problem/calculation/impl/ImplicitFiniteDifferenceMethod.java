package problem.calculation.impl;

import problem.calculation.FiniteDifferenceMethod;
import problem.models.Matrix;
import problem.solution.Solution;
import problem.utils.FieldConfiguration;
import problem.utils.matrix.solver.LinearEquationsSystemSolver;
import problem.utils.matrix.solver.impl.TridiagonalMatrixAlgorithm;

public class ImplicitFiniteDifferenceMethod implements FiniteDifferenceMethod {
    @Override
    public FieldConfiguration solve(FieldConfiguration configuration, LinearEquationsSystemSolver solver) {
        TridiagonalMatrixAlgorithm algorithm = new TridiagonalMatrixAlgorithm();
        Solution solution = new Solution();
        Matrix matrix = configuration.matrix;
        double gamma = 1;//Constants.a_sqr * configuration.timeStep / configuration.lengthStep / configuration.lengthStep;

        double[][]  table = new double[matrix.getN()-1][matrix.getN()-1];
        double[] y = new double[matrix.getN()-1];
        double[] x = new double[matrix.getN()-1];

        for (int i = 1; i < matrix.getN() - 1; i++) {
            for (int j = 0; j < matrix.getM() - 1; j++) {

            }
            x = algorithm.solve(table, y);
            for (int j = 0; j < matrix.getM() - 1; j++) {

            }
        }

        return null;
    }
}
