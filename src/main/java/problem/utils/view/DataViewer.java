package problem.utils.view;

import problem.utils.FieldConfiguration;
import problem.utils.Loggable;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataViewer extends Loggable {
    void view(FieldConfiguration configuration) throws FileNotFoundException, IOException;
}
