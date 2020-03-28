package problem.utils.view.impl;

import problem.Constants;
import problem.utils.FieldConfiguration;
import problem.utils.LogMessage;
import problem.utils.view.DataViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            List<String> buffer = new ArrayList<>();
            String temp = "\t";


            // Print the line of length range
            for (int i = 0; i < configuration.n; i++) {
                temp = temp.concat(Double.toString(i * configuration.lengthStep)).concat("\t");
            }
            buffer.add(temp.concat("\n"));

            // Print all table of data
            for (int i = configuration.m - 1; i >= 0; i--) {
                // Show current time step
                temp = Double.toString(i * configuration.timeStep).concat("\t");

                // Show the line of calculated data
                for (int j = 0; j < configuration.n; j++) {
                    temp = temp.concat(Double.toString(configuration.matrix[i][j])).concat("\t");
                }
                buffer.add(temp.concat("\n"));
            }

            // Write all data lines into the file
            for (String row : buffer) {
                writer.write(row.replace(".", ","));
            }

            log.info(LogMessage.DATA_SERIALIZER_DONE.getMessageString());
        }
    }
}
