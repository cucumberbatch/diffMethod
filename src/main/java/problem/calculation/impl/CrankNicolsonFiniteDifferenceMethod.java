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

    @Override
    public FieldConfiguration solve(FieldConfiguration conf) {
        Solution solution = new Solution();
        double[][] matrix = conf.matrix;
        double gamma = Constants.a_sqr * conf.timeStep / conf.lengthStep / conf.lengthStep;
        double lambda  = (conf.lengthStep * conf.lengthStep) / conf.timeStep;

        double[][] A = new double[conf.n-2][conf.n-2];
        double[]   y = new double[conf.n-2];
        double[]   x;

        log.info(LogMessage.DIFF_METHOD_CALCULATING.getMessageString());

        // Loop by all the time elements
        for (int m = 1; m < conf.m; m++) {
            // Loop by the length elements to fill the SoLE (System of Linear Equations)
            for (int n = 1; n < conf.n-1; n++) {
                // Collect data into left and top elements
                if (n > 1) {
                    A[n-1][n-2] = A[n-2][n-1] = Constants.a_sqr;
                }
                // Collect data into middle and y elements
                A[n-1][n-1] = -2 * (Constants.a_sqr + lambda);
                y[n-1] = 2 * (Constants.a_sqr - lambda) * matrix[m-1][n]
                        - Constants.a_sqr * (matrix[m-1][n-1] + matrix[m-1][n+1])
                        + 2 * conf.lengthStep * conf.lengthStep * solution.u((n-1) * conf.lengthStep, (m-1) * conf.timeStep);
            }

            // Subtract from the first and last rows
            y[0]            -= Constants.a_sqr * matrix[m-1][0];
            y[y.length-1]   -= Constants.a_sqr * matrix[m-1][conf.n-1];

            // Solve the current SoLE
            x = algorithm.solve(A, y);

            // Insert calculated data
            if (conf.n - 2 >= 0) System.arraycopy(x, 0, matrix[m], 1, conf.n - 2);
        }

        log.info(LogMessage.DIFF_METHOD_DONE.getMessageString());

        return null;
    }
}