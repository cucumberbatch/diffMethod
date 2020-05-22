package problem.models;

import math.Function;
import math.calculations.series.Fourier;
import problem.utils.FieldConfiguration;
import problem.utils.view.DataPrinter;
import problem.utils.view.impl.ConsoleDataPrinter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;

// TODO: need a constructor with right initialization of field configuration object
public class ReactionDiffusionProblem {
    private FieldConfiguration configuration;
    private Function[] functions;
    private Function[] conditions;
    private int functionsNumber;

    public Function[] getFunctions() {
        return functions;
    }

    public void setFunctions(Function[] functions) {
        if (functions == null) return;
        this.functions = functions;
        this.functionsNumber = functions.length;
    }

    public Function[] getConditions() {
        return conditions;
    }

    public void setConditions(Function[] conditions) {
        this.conditions = conditions;
    }

    public int getFunctionsNumber() {
        return functionsNumber;
    }

    public void display() throws IOException {
        this.display(new ConsoleDataPrinter());
    }

    public void display(DataPrinter printer) throws IOException {
        printer.print(this.configuration);
    }

    // TODO: need some clean-up
    public void calculate() {
        int pts = 315;
        double[] fi;
        double[] a = new double[pts];

        double dx = 0.1d;
        double[] x = new double[pts];
        Function f = (X, A) -> X < 0.5d ? X : X - 0.5d;
        for (int i = 0; i < pts; i++) {
            x[i] = dx * i;
            a[i] = f.value(x[i]);
        }

        double[] coefficients = Fourier.transform(a, 0, PI);
        fi = Fourier.inverseTransform(coefficients, x);

        List<Integer> index = new ArrayList<>();
        List<Double> harmonics = new ArrayList<>();
        for (int i = 0; i < coefficients.length; i++) {
            index.add(i);
            harmonics.add(coefficients[i]);
        }

        System.out.println("~  H a r m o n i c s  ~");
        for (int i = 0; i < index.size(); i++) {
            System.out.println(index.get(i) + "\t|\t" + harmonics.get(i));
        }

        System.out.println("-  V a l u e s  -");
        for (int i = 0; i < pts; i++) {
            System.out.println(x[i] + "\t|\t" + a[i] + "\t|\t" + fi[i]);
        }
    }
}
