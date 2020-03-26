package problem.utils.view.impl;

import problem.Constants;
import problem.utils.FieldConfiguration;
import problem.utils.LogMessage;
import problem.utils.view.DataViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class DataFileSerializer implements DataViewer {
    private Logger log;
    private File file;

    public DataFileSerializer(String fileName) {
        file = new File(Constants.DATA_FOLDER + fileName + ".dat");
    }

    @Override
    public void setLogger(Logger log) {
        this.log = log;
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
                    writer.write(Double.toString(configuration.matrix[i][j]).replace('.', ',') + "\t");
                }

                writer.write("\n");

            }
            log.info(LogMessage.DATA_SERIALIZER_DONE.getMessageString());
        }
    }
}
