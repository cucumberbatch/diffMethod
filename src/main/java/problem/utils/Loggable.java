package problem.utils;

import java.util.logging.Logger;

/**
 * Interface that allows to attach logger to methods where it needs
 */
public interface Loggable {
    default void setLogger(Logger log) {};
}
