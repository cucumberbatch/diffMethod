package problem.calculation;

import problem.utils.FieldConfiguration;
import problem.utils.Loggable;

/**
 * Interface for implementing difference methods
 */
public interface FiniteDifferenceMethod extends Loggable {
    FieldConfiguration solve(FieldConfiguration configuration);
}
