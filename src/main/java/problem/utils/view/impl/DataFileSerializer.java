package problem.utils.view.impl;

import problem.Constants;
import problem.utils.FieldConfiguration;
import problem.utils.view.DataViewer;

import java.io.*;

public class DataFileSerializer implements DataViewer {
    File file;

    public DataFileSerializer(String fileName) {
        file = new File(Constants.DATA_FOLDER + fileName + ".dat");
    }

    @Override
    public void view(FieldConfiguration configuration) throws FileNotFoundException, IOException {
        try (FileWriter writer = new FileWriter(file, false)) {

            // TODO: replace repeated writer method calls with a single writer method call
            writer.write("\t");

            // Print the line of length range
            for (int i = 0; i < configuration.n; i++) {
                writer.write(Double.toString(i * configuration.lengthStep).replace('.', ',') + "\t");
            }

            writer.write("\n");

            // Print all table of data
            for (int i = configuration.m - 1; i >= 0; i--) {
                // Show current time step
                writer.write(Double.toString(i * configuration.timeStep).replace('.', ',') + "\t");

                // Show the line of calculated data
                for (int j = 0; j < configuration.n; j++) {
                    writer.write(Double.toString(configuration.matrix.value(i, j)).replace('.', ',') + "\t");
                }

                writer.write("\n");
            }
        }
    }
}
