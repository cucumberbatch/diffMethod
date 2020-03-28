package problem.calculation.impl;

import problem.Constants;
import problem.calculation.FiniteDifferenceMethod;
import problem.solution.Solution;
import problem.utils.FieldConfiguration;
import problem.utils.LogMessage;
import problem.utils.matrix.solver.LinearEquationsSystemSolver;

import java.util.logging.Logger;

public class CrankNicolsonFiniteDifferenceMethod implements FiniteDifferenceMethod {
    private LinearEquationsSystemSolver algorithm;
    private Logger log;

    public CrankNicolsonFiniteDifferenceMethod(LinearEquationsSystemSolver algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public void setLogger(Logger log) {
        this.log = log;
    }

    // FIXME: hurry up.. we need to make it work correctly
    @Override
    public FieldConfiguration solve(FieldConfiguration conf) {
        Solution solution = new Solution();
        double[][] matrix = conf.matrix;
        double gamma = Constants.a_sqr * conf.timeStep / conf.lengthStep / conf.lengthStep;
        double beta_p  = Constants.a_sqr + (conf.lengthStep * conf.lengthStep) / conf.timeStep;
        double beta_m  = Constants.a_sqr - (conf.lengthStep * conf.lengthStep) / conf.timeStep;

        double[][] A = new double[conf.n-2][conf.n-2];
        double[]   y = new double[conf.n-2];
        double[]   x;

        log.info(LogMessage.DIFF_METHOD_CALCULATING.getMessageString());

        // Loop by all the time elements
        for (int m = 1; m < conf.m; m++) {

            // Loop by the length elements to fill the SoLE (System of Linear Equations)
            for (int n = 2; n < conf.n-3; n++) {
                A[n  ][n-1]   = Constants.a_sqr;
                A[n  ][n  ]   = -2 * beta_p;
                A[n  ][n+1]   = Constants.a_sqr;

                y[n] =  2 * beta_m * matrix[m][n] -
                        Constants.a_sqr * (matrix[m][n-1] + matrix[m][n+1]) +
                        2 * conf.lengthStep * conf.lengthStep * solution.u(n * conf.lengthStep, m * conf.timeStep);
            }

            // First row of the SoLE
            A[0][0] = Constants.a_sqr;
            A[0][1] = -2 * beta_p;
            y[0] =  2 * beta_m * matrix[m][1] -
                    Constants.a_sqr * (matrix[m][0] + matrix[m][2]) +
                    2 * conf.lengthStep * conf.lengthStep * solution.u(0, m * conf.timeStep) -
                    Constants.a_sqr * matrix[m][0];

            // Last row of the SoLE
            A[A.length-1][A.length-2] = -2 * beta_p;
            A[A.length-1][A.length-1] = Constants.a_sqr;
            y[y.length-1] =  2 * beta_m * matrix[m][conf.n-2] -
                    Constants.a_sqr * (matrix[m][conf.n-3] + matrix[m][conf.n-1]) +
                    2 * conf.lengthStep * conf.lengthStep * solution.u(conf.length, m * conf.timeStep) -
                    Constants.a_sqr * matrix[m][conf.n-1];

            // Solve the current SoLE
            x = algorithm.solve(A, y);

            // Insert calculated data
            if (conf.n - 1 - 1 >= 0) System.arraycopy(x, 0, matrix[m], 1, conf.n - 1 - 1);
        }

        log.info(LogMessage.DIFF_METHOD_DONE.getMessageString());

        return null;
    }

}
