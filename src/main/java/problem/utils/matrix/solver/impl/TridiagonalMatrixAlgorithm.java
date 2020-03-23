package problem.utils.matrix.solver.impl;

import problem.utils.matrix.solver.LinearEquationsSystemSolver;

public class TridiagonalMatrixAlgorithm implements LinearEquationsSystemSolver {
    @Override
    public double[] solve(double[][] matrix, double[] vector) {
        double[] lambda = new double[matrix.length];
        double[] delta = new double[matrix.length];
        double[] delta_prime = new double[matrix.length];
        double[] x = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            if (i != 0) {
                delta_prime[i] = matrix[i][i] + matrix[i][i-1] * delta[i-1];
                delta[i] = -(matrix[i-1][i] / delta_prime[i]);
                lambda[i] = (vector[i] - matrix[i][i-1] * lambda[i-1]) / delta_prime[i];
            }
            else {
                delta_prime[0] = matrix[0][0];
                delta[0] = -(matrix[0][1] / delta_prime[0]);
                lambda[0] = vector[0] / delta_prime[0];
            }
        }

        for (int i = matrix.length - 1; i > -1; i--) {
            if (i < matrix.length - 1) {
                x[i] = delta[i] * x[i+1] + lambda[i];
            }
            else {
                x[i] = delta[i];
            }
        }

        return x;
    }

}
