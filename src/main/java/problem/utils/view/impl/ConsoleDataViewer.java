package problem.utils.view.impl;

import problem.utils.FieldConfiguration;
import problem.utils.view.DataViewer;

import java.util.logging.Logger;

public class ConsoleDataViewer implements DataViewer {

    @Override
    public void setLogger(Logger log) {
    }

    @Override
    public void view(FieldConfiguration configuration) {

        // TODO: replace repeated console method calls with a single output method call
        System.out.print("\t");

        // Print the line of length range
        for (int n = 0; n < configuration.n; n++) {
            System.out.print(n * configuration.lengthStep + "\t");
        }

        System.out.print('\n');

        // Print all table of data
        for (int m = configuration.m - 1; m >= 0; m--) {
            // Show current time step
            System.out.print(m * configuration.timeStep + "\t");

            // Show the line of calculated data
            for (int n = 0; n < configuration.n; n++) {
                System.out.print(configuration.matrix[m][n] + "\t");
            }

            System.out.print('\n');

        }
        System.out.print('\n');
    }
}
