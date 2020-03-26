package problem.utils.matrix.solver.impl;

import problem.utils.matrix.solver.LinearEquationsSystemSolver;

public class TridiagonalMatrixAlgorithm implements LinearEquationsSystemSolver {
    @Override
    public double[] solve(double[][] matrix, double[] vector) {
        double[] beta   = new double[matrix.length];
        double[] alpha  = new double[matrix.length];
        double[] x      = new double[matrix.length];

        alpha[0] = -(matrix[0][1] / matrix[0][0]);
        beta[0] = vector[0] / matrix[0][0];

        for (int i = 1; i < matrix.length; i++) {
            alpha[i] = -(matrix[i-1][i] / (matrix[i][i] + (matrix[i][i-1] * alpha[i-1])));
            beta[i] = (vector[i] - (matrix[i][i-1] * beta[i-1])) / (matrix[i][i] + (matrix[i][i-1] * alpha[i-1]));
        }

        x[x.length-1] = beta[x.length-1];
        for (int i = matrix.length - 2; i > -1; i--) {
            x[i] = (alpha[i] * x[i+1]) + beta[i];
        }

        return x;
    }
}
