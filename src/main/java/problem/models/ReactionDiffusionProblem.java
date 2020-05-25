package problem.models;

import math.Function;
import math.calculations.series.Fourier;
import problem.utils.FieldConfiguration;
import problem.utils.view.DataPrinter;
import problem.utils.view.impl.ConsoleDataPrinter;

import java.io.IOException;

// TODO: need a constructor with right initialization of field configuration object
public class ReactionDiffusionProblem {
    private FieldConfiguration configuration;
    private Function[] functions;
    private Function[] conditions;
    private double[] diffusion;


    public ReactionDiffusionProblem(FieldConfiguration configuration, Function[] functions, Function[] conditions, double[] diffusion) {
        this(configuration);
        this.setFunctions(functions);
        this.setConditions(conditions);
        this.setDiffusion(diffusion);
    }

    public ReactionDiffusionProblem(FieldConfiguration configuration) {
        this.configuration = configuration;
    }

    public ReactionDiffusionProblem(int x, double dx, int t, double dt) {
        this(new FieldConfiguration(new double[x][t], dx, dt));
    }

    public Function[] getFunctions() {
        return functions;
    }

    public void setFunctions(Function[] functions) {
        if (functions == null) return;
        this.functions = functions;
    }

    public Function[] getConditions() {
        return conditions;
    }

    private void setDiffusion(double[] diffusion) {
        this.diffusion = diffusion;
    }

    public void setConditions(Function[] conditions) {
        if (conditions == null) return;
        this.conditions = conditions;
    }

    public int getFunctionsNumber() {
        return functions.length;
    }

    public void display() throws IOException {
        this.display(new ConsoleDataPrinter());
    }

    public void display(DataPrinter printer) throws IOException {
        printer.print(this.configuration);
    }

    // TODO: need some clean-up
    public void calculate() {
        double[][][] u = new double[functions.length][configuration.m][configuration.n];
        double[][] fi = new double[configuration.m][Fourier.N];
        double[][] a = new double[configuration.m][Fourier.N];
        double[][] table = configuration.matrix;

        // итерироваие по функциям для первого слоя
        // . . .

            // нахождение значений данной функции при начальных условиях
            // . . .

        // итерирование по слоям начиная со второго
        // . . .

            // итерироваие по функциям
            // . . .

                // итерирование по функцям распределения
                // . . .

                    // нахождение Фурье образа для каждого распределения
                    // . . .

                // нахождение значений функции при полученных значениях распределения
                // . . .


//        for (int equationIndex = 0; equationIndex < functions.length; equationIndex++) {
//            for (int n = 0; n < configuration.n; n++) {
//                table[0][n] = functions[]conditions[0].value(configuration.lengthStep * n);
//            }
//
//            double factor = configuration.timeStep * diffusion[equationIndex] * PI * PI / configuration.length / configuration.length;
//
//            for (int layerIndex = 1; layerIndex < configuration.m; layerIndex++) {
//                fi[layerIndex] = Fourier.transform(table[layerIndex - 1], 0, configuration.length);
//                for (int coefficientIndex = 0; coefficientIndex < Fourier.N; coefficientIndex++) {
//                    a[layerIndex][coefficientIndex] = (1 / (factor * coefficientIndex * coefficientIndex - 1))
//                                    * (a[layerIndex - 1][coefficientIndex] + configuration.timeStep * fi[layerIndex][coefficientIndex]);
//                }
//                u[equationIndex][layerIndex] = Fourier.inverseTransform(a[layerIndex], configuration.lengthStep);
//            }
//        }
    }
}
