package problem.utils.view.impl;

import problem.utils.FieldConfiguration;
import problem.utils.view.DataViewer;

public class ConsoleDataViewer implements DataViewer {
    @Override
    public void view(FieldConfiguration configuration) {
        System.out.print("\t");

        // Print the line of length range
        for (int i = 0; i < configuration.m; i++) {
            System.out.print(i * configuration.lengthStep + "\t");
        }
        System.out.print('\n');

        // Print
        for (int i = configuration.n - 1; i >= 0; i--) {
            // Show current time step
            System.out.print(i * configuration.timeStep + "\t");

            // Show the line of
            for (int j = 0; j < configuration.m; j++) {
                System.out.print(configuration.matrix.value(i, j) + "\t");
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }
}
