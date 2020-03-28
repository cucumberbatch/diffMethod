package problem.utils.view;

import problem.utils.FieldConfiguration;
import problem.utils.Loggable;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataPrinter extends Loggable {
    void print(FieldConfiguration configuration) throws FileNotFoundException, IOException;
}
