package problem.utils.view.impl;

import problem.Constants;
import problem.utils.FieldConfiguration;
import problem.utils.view.DataViewer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConsoleDataViewer implements DataViewer {
    @Override
    public void view(FieldConfiguration configuration) {
        DecimalFormat formatter = new DecimalFormat(Constants.CONSOLE_OUTPUT_FORMAT);
        List<String> buffer = new ArrayList<>();
        String temp = "\n   --  \t| ";


        // Print the line of length range
        for (int n = 0; n < configuration.n; n++) {
            temp = temp.concat(formatter.format(n * configuration.lengthStep) + "\t");
        }
        buffer.add(temp + "\n");

        // Print all table of data
        for (int m = configuration.m - 1; m >= 0; m--) {
            // Show current time step
            temp = formatter.format(m * configuration.timeStep).concat("\t| ");

            // Show the line of calculated data
            for (int n = 0; n < configuration.n; n++) {
                temp = temp.concat(formatter.format(configuration.matrix[m][n]) + "\t");
            }
            buffer.add(temp + "\n");
        }

        System.out.print(buffer);
    }
}
