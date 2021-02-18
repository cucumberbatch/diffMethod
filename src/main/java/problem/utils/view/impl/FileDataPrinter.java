package problem.utils.view.impl;

import problem.Constants;
import problem.utils.FieldConfiguration;
import problem.utils.view.DataPrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDataPrinter implements DataPrinter {
    private File file;

    public FileDataPrinter(String fileName) {
        file = new File(Constants.DATA_FOLDER.concat(fileName).concat(".dat"));
    }

    @Override
    public void print(FieldConfiguration configuration) throws FileNotFoundException, IOException {
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
        }
    }
}
