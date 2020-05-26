package problem.models;

import math.Function;
import math.calculations.series.Fourier;
import problem.utils.FieldConfiguration;
import problem.utils.view.DataPrinter;
import problem.utils.view.impl.ConsoleDataPrinter;

import java.io.IOException;

import static java.lang.Math.PI;

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

    public void setDiffusion(double[] diffusion) {
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

    // TODO: needs to check for the correct working
    public void calculate() {
        double[][][] u  = new double[configuration.m][configuration.n][functions.length];
        double[][][] f  = new double[configuration.m][configuration.n][functions.length];
        double[][][] a  = new double[configuration.m][functions.length][Fourier.N];
        double[][][] fi = new double[configuration.m][functions.length][Fourier.N];
        double[][] table = configuration.matrix;

        // итерирование по всем функциям распределения при начальных условиях
        for (int distributionFunctionIndex = 0; distributionFunctionIndex < conditions.length; distributionFunctionIndex++) {

            // массив для начального распределения
            double[] initialDistribution = new double[configuration.n];

            // итерирование по всей длине слоя
            for (int lengthIndex = 0; lengthIndex < configuration.lengthStep; lengthIndex++) {

                // дискретизация начальных условий, заданных в аналитическом виде
                initialDistribution[lengthIndex] = conditions[distributionFunctionIndex].value(lengthIndex * configuration.lengthStep);
                u[0][lengthIndex][distributionFunctionIndex] = initialDistribution[lengthIndex];
            }

            // разложение начального распределения на коэффициенты Фурье
            a[0][distributionFunctionIndex] = Fourier.transform(initialDistribution, 0, configuration.length);
        }

        // итерирование по остальным слоям
        for (int layerIndex = 1; layerIndex < configuration.m; layerIndex++) {

            // итерироваие по функциям распределения
            for (int functionIndex = 0; functionIndex < functions.length; functionIndex++) {

                // массив для значений функций, зависящих от распределения на предыдущем слое
                double[] temporaryFunction = new double[configuration.n];

                // итерирование по всей длине поля
                for (int lengthIndex = 0; lengthIndex < configuration.n; lengthIndex++) {

                    // нахождение значений данной функции при значениях распределения с предыдущего слоя
                    temporaryFunction[lengthIndex] = functions[functionIndex].value(0, u[layerIndex - 1][lengthIndex]);
                    f[layerIndex][lengthIndex][functionIndex] = temporaryFunction[lengthIndex];
                }

                // нахождение Фурье образа для каждого распределения
                fi[layerIndex][functionIndex] = Fourier.transform(temporaryFunction, 0, configuration.length);

                // вынесение некоторого множителя "за скобки" для уменьшения вычислительной нагрузки
                double factor = configuration.timeStep * diffusion[functionIndex] * PI * PI / configuration.length / configuration.length;

                // нахождение коэффициентов Фурье для функций распределения
                for (int coefficientIndex = 0; coefficientIndex < Fourier.N; coefficientIndex++) {
                    a[layerIndex][functionIndex][coefficientIndex] =
                            (1 / (factor * coefficientIndex * coefficientIndex - 1))
                            * (a[layerIndex - 1][functionIndex][coefficientIndex]
                            + configuration.timeStep * fi[layerIndex][functionIndex][coefficientIndex]);
                }

                // нахождение значений функции распределения через обратное преобразование Фурье
                double[] temporaryDistribution = Fourier.inverseTransform(a[layerIndex][functionIndex], configuration.lengthStep);

                // запись полученных значений в массив
                for (int lengthIndex = 0; lengthIndex < configuration.n; lengthIndex++) {
                    u[layerIndex][lengthIndex][functionIndex] = temporaryDistribution[lengthIndex];
                }
            }
        }


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
