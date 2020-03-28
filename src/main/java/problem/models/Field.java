package problem.models;

import problem.calculation.FiniteDifferenceMethod;
import problem.conditions.BoundaryCondition;
import problem.utils.FieldConfiguration;
import problem.utils.view.DataPrinter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Field {
    private FieldConfiguration configuration;
    private static Logger log = null;

    //Logger initialization with property file in resources package
    static {
        InputStream stream = Field.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
            log = Logger.getLogger(Field.class.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Field(FieldConfiguration configuration) {
        this.configuration = configuration;
    }

    public Field(double length, double time, int n, int m) {
        configuration = new FieldConfiguration(new double[m+1][n+1], length / n, time / m);
    }

    public void applyBoundaryCondition(BoundaryCondition condition) {
        condition.setLogger(log);
        condition.apply(configuration);
    }

    public void applyDifferenceMethod(FiniteDifferenceMethod method) {
        method.setLogger(log);
        method.solve(configuration);
    }

    public void viewDataOn(DataPrinter printer) throws IOException {
        printer.setLogger(log);
        printer.print(configuration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return configuration.equals(field.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configuration);
    }
}
