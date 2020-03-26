package problem.calculation.impl;

import problem.Constants;
import problem.calculation.FiniteDifferenceMethod;
import problem.solution.Solution;
import problem.utils.FieldConfiguration;
import problem.utils.LogMessage;

import java.util.logging.Logger;

public final class ExplicitFiniteDifferenceMethod implements FiniteDifferenceMethod {
    private Logger log;

    @Override
    public void setLogger(Logger log) {
        this.log = log;
    }

    @Override
    public FieldConfiguration solve(FieldConfiguration configuration) {
        Solution solution = new Solution();
        double[][] matrix = configuration.matrix;
        double gamma = Constants.a_sqr * configuration.timeStep / configuration.lengthStep / configuration.lengthStep;

        if (gamma > 0.5d) {
            log.warning(LogMessage.DIFF_METHOD_INACCURACY.getMessageString() + " :\tgamma = " + Double.toString(gamma));
        }

        for (int m = 0; m < configuration.m-1; m++) {
            for (int n = 1; n < configuration.n-1; n++) {
                matrix[m+1][n] = (
                        matrix[m][n-1] * gamma +                    // left bottom point
                        matrix[m][n+1] * gamma +                    // right bottom point
                        matrix[m][n  ] * (1 - 2 * gamma) +            // center bottom point
                        solution.u(n, m) * configuration.timeStep   // predict solution in point
                );
            }
        }

        log.info(LogMessage.DIFF_METHOD_DONE.getMessageString());

        return new FieldConfiguration(matrix, configuration.lengthStep, configuration.timeStep);
    }
}
