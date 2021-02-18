package problem.utils.view;

import problem.utils.FieldConfiguration;

import java.io.IOException;

public interface DataPrinter {
    void print(FieldConfiguration configuration) throws IOException;
}
