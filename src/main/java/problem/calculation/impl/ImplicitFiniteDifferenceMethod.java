package problem.calculation.impl;

import problem.Constants;
import problem.calculation.FiniteDifferenceMethod;
import problem.solution.Solution;
import problem.utils.FieldConfiguration;
import problem.utils.LogMessage;
import problem.utils.matrix.solver.LinearEquationsSystemSolver;
import problem.utils.matrix.solver.impl.GaussMatrixAlgorithm;
import problem.utils.matrix.solver.impl.TridiagonalMatrixAlgorithm;

import java.util.logging.Logger;

public class ImplicitFiniteDifferenceMethod implements FiniteDifferenceMethod {
    private LinearEquationsSystemSolver algorithm;
    private Logger log;

    public ImplicitFiniteDifferenceMethod(LinearEquationsSystemSolver algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public void setLogger(Logger log) {
        this.log = log;
    }

    @Override
    public FieldConfiguration solve(FieldConfiguration configuration) {
        Solution solution = new Solution();
        double[][] matrix = configuration.matrix;
        double gamma = Constants.a_sqr * configuration.timeStep / configuration.lengthStep / configuration.lengthStep;

        double[][] A = new double[configuration.n-2][configuration.n-2];
        double[]   y = new double[configuration.n-2];
        double[]   x;

        for (int m = 1; m < configuration.m; m++) {
            for (int n = 1; n < configuration.n-3; n++) {
                A[n  ][n-1]   = -solution.u(n-1, m) * gamma;
                A[n  ][n+1]   = -solution.u(n+1, m) * gamma;
                A[n  ][n  ]   =  solution.u(n,   m) * (1 + 2 * gamma);

                y[n] = -solution.u(1, 0) * gamma;
            }
            A[0][0] =  solution.u(0, m) * (1 + 2 * gamma);
            A[0][1] = -solution.u(1, m) * gamma;
            A[A.length-1][A.length-2] = -solution.u(configuration.n-2, m) * gamma;
            A[A.length-1][A.length-1] =  solution.u(configuration.n-1, m) * (1 + 2 * gamma);

            y[0] = -solution.u(1, 0) * gamma - solution.u(0, m);
            y[y.length-1] = -solution.u(1, 0) * gamma - solution.u(configuration.n, m);

            x = algorithm.solve(A, y);

            for (int n = 1; n < configuration.n-1; n++) {
                matrix[m][n] = x[n-1] * solution.u(n, m);
            }
        }

        log.info(LogMessage.DIFF_METHOD_DONE.getMessageString());

        return null;
    }
}
