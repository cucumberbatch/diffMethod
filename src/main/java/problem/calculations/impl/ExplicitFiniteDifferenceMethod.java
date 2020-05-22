package problem.calculations.impl;

import problem.Constants;
import problem.calculations.FiniteDifferenceMethod;
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
    public FieldConfiguration solve(FieldConfiguration conf) {
        Solution solution = new Solution();
        double[][] matrix = conf.matrix;
        double gamma = Constants.a_sqr * conf.timeStep / conf.lengthStep / conf.lengthStep;

        if (gamma > 0.5d) {
            log.warning(LogMessage.DIFF_METHOD_INACCURACY.getMessageString() + " :\tgamma = " + gamma);
        }

        log.info(LogMessage.DIFF_METHOD_CALCULATING.getMessageString());

        for (int m = 0; m < conf.m-1; m++) {
            for (int n = 1; n < conf.n-1; n++) {
                matrix[m+1][n] = (
                        matrix[m][n-1] * gamma +                    // left bottom point
                        matrix[m][n+1] * gamma +                    // right bottom point
                        matrix[m][n  ] * (1 - 2 * gamma) +          // center bottom point
                        solution.u(n * conf.lengthStep, m * conf.timeStep) * conf.timeStep
                );
            }
        }

        log.info(LogMessage.DIFF_METHOD_DONE.getMessageString());

        return new FieldConfiguration(matrix, conf.lengthStep, conf.timeStep);
    }
}
