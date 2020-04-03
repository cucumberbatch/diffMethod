package problem.utils.matrix.solver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import problem.utils.matrix.solver.impl.GaussMatrixAlgorithm;
import problem.utils.matrix.solver.impl.TridiagonalMatrixAlgorithm;

class MatrixAlgorithmsTest {

    private static final double DELTA = 1E-5;

    @Test
    void testGaussMatrixAlgorithm1() {
        GaussMatrixAlgorithm algorithm = new GaussMatrixAlgorithm();
        double[][] A = {
                { 1,  2,  3},
                { 4,  5,  6},
                { 1,  0,  1}
        };
        double[] y = { 1, 1, 1 };
        double[] result = { 0, -1,  1 };
        Assertions.assertArrayEquals(result, algorithm.solve(A, y), DELTA);
    }

    @Test
    void testGaussMatrixAlgorithm2() {
        GaussMatrixAlgorithm algorithm = new GaussMatrixAlgorithm();
        double[][] A = {
                { 2, -1,  0,  0,  0},
                {-3,  8, -1,  0,  0},
                { 0, -5, 12,  2,  0},
                { 0,  0, -6, 18, -4},
                { 0,  0,  0, -5, 10}
        };
        double[] y = { -25, 72, -69, -156, 20 };
        double[] result = { -10, 5, -2, -10, -3 };
        Assertions.assertArrayEquals(result, algorithm.solve(A, y), DELTA);
    }

    @Test
    void testGaussMatrixAlgorithm3() {
        GaussMatrixAlgorithm algorithm = new GaussMatrixAlgorithm();
        double[][] A = {
                { 2,  1,  0,  0,  0},
                { 2,  9,  2,  0,  0},
                { 0,  4, 17, -4,  0},
                { 0,  0,  4, 15, -8},
                { 0,  0,  0,  2,  3}
        };
        double[] y = { -10, -26, -16, -2, 16 };
        double[] result = { -4, -2, 0, 2, 4 };
        Assertions.assertArrayEquals(result, algorithm.solve(A, y), DELTA);
    }

    @Test
    void testTridiagonalMatrixAlgorithm1() {
        TridiagonalMatrixAlgorithm algorithm = new TridiagonalMatrixAlgorithm();
        double[][] A = {
                { 2, -1,  0,  0,  0},
                {-3,  8, -1,  0,  0},
                { 0, -5, 12,  2,  0},
                { 0,  0, -6, 18, -4},
                { 0,  0,  0, -5, 10}
        };
        double[] y = { -25, 72, -69, -156, 20 };
        double[] result = { -10, 5, -2, -10, -3 };
        Assertions.assertArrayEquals(result, algorithm.solve(A, y), DELTA);
    }

    @Test
    void testTridiagonalMatrixAlgorithm2() {
        TridiagonalMatrixAlgorithm algorithm = new TridiagonalMatrixAlgorithm();
        double[][] A = {
                { 2,  1,  0,  0,  0},
                { 2,  9,  2,  0,  0},
                { 0,  4, 17, -4,  0},
                { 0,  0,  4, 15, -8},
                { 0,  0,  0,  2,  3}
        };
        double[] y = { -10, -26, -16, -2, 16 };
        double[] result = { -4, -2, 0, 2, 4 };
        Assertions.assertArrayEquals(result, algorithm.solve(A, y), DELTA);
    }

}