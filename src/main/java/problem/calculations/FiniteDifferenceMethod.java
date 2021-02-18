package problem.calculations;

import problem.utils.FieldConfiguration;

/**
 * Interface for implementing difference methods
 */
public interface FiniteDifferenceMethod {
    FieldConfiguration solve(FieldConfiguration configuration);
}
