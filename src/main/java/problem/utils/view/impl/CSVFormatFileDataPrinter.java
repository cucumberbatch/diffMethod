package problem.utils.view.impl;

import problem.Constants;
import problem.utils.FieldConfiguration;
import problem.utils.view.DataPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static problem.Constants.CSV_TABULATION_SEPARATION_CHARACTER;

public class CSVFormatFileDataPrinter implements DataPrinter {
    private char separationCharacter = '\t';
    private File file;

    public CSVFormatFileDataPrinter(String fileName) {
        // invoke constructor with tabulation character as a separator by default
        this(fileName, CSV_TABULATION_SEPARATION_CHARACTER);
    }

    public CSVFormatFileDataPrinter(String fileName, char separationCharacter) {
        file = new File(Constants.DATA_FOLDER.concat(fileName).concat(".dat"));
        if (separationCharacter != 0) {
            this.separationCharacter = separationCharacter;
        }
    }

    @Override
    public void print(FieldConfiguration configuration) throws IOException {
        try (FileWriter writer = new FileWriter(file, false)) {
            List<StringBuffer> buffer = new ArrayList<>();
            StringBuffer temp = new StringBuffer(String.valueOf(separationCharacter));


            // Print the line of length range
            for (int i = 0; i < configuration.n; i++) {
                temp.append(i * configuration.lengthStep).append(separationCharacter);
            }
            buffer.add(temp.append("\n"));

            // Print all table of data
            for (int i = configuration.m - 1; i >= 0; i--) {
                // Show current time step
                temp = new StringBuffer().append(i * configuration.timeStep).append(separationCharacter);

                // Show the line of calculated data
                for (int j = 0; j < configuration.n; j++) {
                    temp.append(configuration.matrix[i][j]).append(separationCharacter);
                }
                buffer.add(temp.append("\n"));
            }

            // Write all data lines into the file
            for (StringBuffer row : buffer) {
                writer.write(row.toString());
            }
        }
    }
}
