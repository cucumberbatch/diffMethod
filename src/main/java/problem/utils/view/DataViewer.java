package problem.utils.view;

import problem.utils.FieldConfiguration;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataViewer {
    void view(FieldConfiguration configuration) throws FileNotFoundException, IOException;
}
