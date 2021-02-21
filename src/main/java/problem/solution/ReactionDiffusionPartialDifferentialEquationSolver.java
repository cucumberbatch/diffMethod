package problem.solution;

import math.Function;
import math.calculations.series.Fourier;
import math.calculations.series.computing.CosineFourierCore;
import problem.conditions.BoundaryCondition;
import problem.utils.FieldConfiguration;

import static java.lang.Math.PI;

public class ReactionDiffusionPartialDifferentialEquationSolver implements PartialDifferentialEquationSolver {
    @Override
    public void solve(FieldConfiguration configuration) {
//        boolean isTargetDistributionIndexValid = solver.targetDistribution() < functions.length;
//
//        if (!isTargetDistributionIndexValid) return configuration;

        Fourier    fourier    = new Fourier(new CosineFourierCore(), configuration.n);
        Function[] functions  = new Function[1];
        Function[] conditions = new Function[1];
        float   [] diffusion  = new float[1];
        ReactionDiffusionPartialDifferentialEquationSolver solver = new ReactionDiffusionPartialDifferentialEquationSolver();

        // массив для значений функций, зависящих от распределения на предыдущем слое
        double[] temporaryFunction      = new double[configuration.n];
        double[] temporaryDistribution  = new double[configuration.n];

        double[][][] u  = new double[configuration.m][configuration.n][functions.length];
        double[][][] a  = new double[configuration.m][functions.length][fourier.amount()];
        double[][][] fi = new double[configuration.m][functions.length][fourier.amount()];

        int layerIndex;         // переменная индекса слоя
        int lengthIndex;        // переменная индекса элемента длины слоя
        int functionIndex;      // переменная индекса уравнения системы
        int coefficientIndex;   // переменная индекса коэффицинтов Фурье

        // итерирование по всем функциям распределения при начальных условиях
        for (functionIndex = 0; functionIndex < functions.length; functionIndex++) {

            // итерирование по всей длине слоя
            for (lengthIndex = 0; lengthIndex < configuration.n; lengthIndex++) {

                // дискретизация начальных условий, заданных в аналитическом виде
                temporaryDistribution[lengthIndex] = conditions[functionIndex].value(lengthIndex * configuration.lengthStep);
                u[0][lengthIndex][functionIndex] = temporaryDistribution[lengthIndex];
            }

            // разложение начального распределения на коэффициенты Фурье
            a[0][functionIndex] = fourier.transform(temporaryDistribution, 0, configuration.length);
        }

        // итерирование по остальным слоям
        for (layerIndex = 1; layerIndex < configuration.m; layerIndex++) {

            // итерироваие по функциям распределения
            for (functionIndex = 0; functionIndex < functions.length; functionIndex++) {

                // итерирование по всей длине поля
                for (lengthIndex = 0; lengthIndex < configuration.n; lengthIndex++) {

                    // нахождение значений данной функции при значениях распределения с предыдущего слоя
                    temporaryFunction[lengthIndex] = functions[functionIndex].value(0, u[layerIndex - 1][lengthIndex]);
                }

                // нахождение Фурье образа для каждого распределения
                fi[layerIndex][functionIndex] = fourier.transform(temporaryFunction, 0, configuration.length);

                // вынесение некоторого множителя "за скобки" для уменьшения вычислительной нагрузки
                double factor = configuration.timeStep * diffusion[functionIndex] * PI * PI / configuration.length / configuration.length;

                // решение системы в преобразованном виде
                // нахождение коэффициентов Фурье для функций распределения
                for (coefficientIndex = 0; coefficientIndex < fourier.amount(); coefficientIndex++) {
                    a[layerIndex][functionIndex][coefficientIndex] =
                            (1 / (factor * coefficientIndex * coefficientIndex - 1))
                                    * (a[layerIndex - 1][functionIndex][coefficientIndex]
                                    + configuration.timeStep * fi[layerIndex][functionIndex][coefficientIndex]);
                }

                // нахождение значений функции распределения через обратное преобразование Фурье
                temporaryDistribution = fourier.inverseTransform(a[layerIndex][functionIndex], configuration.n, configuration.lengthStep);

                // запись полученных значений в массив
                for (lengthIndex = 0; lengthIndex < configuration.n; lengthIndex++) {
                    u[layerIndex][lengthIndex][functionIndex] = temporaryDistribution[lengthIndex];
                }
            }
        }

        // запись целевой функции распределения в массив для дальнейшего вывода
        for (layerIndex = 0; layerIndex < configuration.m; layerIndex++) {
            for (lengthIndex = 0; lengthIndex < configuration.n; lengthIndex++) {
//                configuration.matrix[layerIndex][lengthIndex] = u[layerIndex][lengthIndex][solver.targetDistribution()];
            }
        }
    }
}
