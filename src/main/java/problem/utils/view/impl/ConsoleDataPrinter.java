package problem.utils.view.impl;

import problem.Constants;
import problem.utils.FieldConfiguration;
import problem.utils.view.DataPrinter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConsoleDataPrinter implements DataPrinter {
    @Override
    public void print(FieldConfiguration configuration) {
        DecimalFormat formatter = new DecimalFormat(Constants.CONSOLE_OUTPUT_FORMAT);
        List<String> buffer = new ArrayList<>();
        String temp = "  --  \t| ";


        // Print the line of length range
        for (int n = 0; n < configuration.n; n++) {
            temp = temp.concat(formatter.format(n * configuration.lengthStep).concat("\t"));
        }
        buffer.add(temp.concat("\n"));

        // Print all table of data
        for (int m = configuration.m - 1; m >= 0; m--) {
            // Show current time step
            temp = formatter.format(m * configuration.timeStep).concat("\t| ");

            // Show the line of calculated data
            for (int n = 0; n < configuration.n; n++) {
                temp = temp.concat(formatter.format(configuration.matrix[m][n]).concat("\t"));
            }
            buffer.add(temp + (m == 0 ? "" : "\n"));
        }

        System.out.print(buffer + "\n");
    }
}
