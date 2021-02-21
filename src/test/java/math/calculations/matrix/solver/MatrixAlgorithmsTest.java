package math.calculations.matrix.solver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import math.calculations.matrix.impl.GaussMatrixAlgorithm;
import math.calculations.matrix.impl.TridiagonalMatrixAlgorithm;

class MatrixAlgorithmsTest {

    private static final double DELTA = 1E-5;

    private static final double[][] matrix1 = {
            { 1,  2,  3 },
            { 4,  5,  6 },
            { 1,  0,  1 }
    };
    private static final double[][] matrix2 = {
            { 2, -1,  0,  0,  0 },
            {-3,  8, -1,  0,  0 },
            { 0, -5, 12,  2,  0 },
            { 0,  0, -6, 18, -4 },
            { 0,  0,  0, -5, 10 }
    };
    private static final double[][] matrix3 = {
            { 2,  1,  0,  0,  0 },
            { 2,  9,  2,  0,  0 },
            { 0,  4, 17, -4,  0 },
            { 0,  0,  4, 15, -8 },
            { 0,  0,  0,  2,  3 }
    };

    private static final double[] y1 = {   1,   1,   1 };
    private static final double[] y2 = { -25,  72, -69, -156, 20 };
    private static final double[] y3 = { -10, -26, -16,   -2, 16 };

    private static final double[] result1 = {  0, -1,  1 };
    private static final double[] result2 = {-10,  5, -2, -10, -3 };
    private static final double[] result3 = { -4, -2,  0,   2,  4 };

    @Test
    void testGaussMatrixAlgorithm1() {
        GaussMatrixAlgorithm algorithm = new GaussMatrixAlgorithm();
        Assertions.assertArrayEquals(result1, algorithm.solve(matrix1, y1), DELTA);
    }

    @Test
    void testGaussMatrixAlgorithm2() {
        GaussMatrixAlgorithm algorithm = new GaussMatrixAlgorithm();
        Assertions.assertArrayEquals(result2, algorithm.solve(matrix2, y2), DELTA);
    }

    @Test
    void testGaussMatrixAlgorithm3() {
        GaussMatrixAlgorithm algorithm = new GaussMatrixAlgorithm();
        Assertions.assertArrayEquals(result3, algorithm.solve(matrix3, y3), DELTA);
    }

    @Test
    void testTridiagonalMatrixAlgorithm1() {
        TridiagonalMatrixAlgorithm algorithm = new TridiagonalMatrixAlgorithm();
        Assertions.assertArrayEquals(result2, algorithm.solve(matrix2, y2), DELTA);
    }

    @Test
    void testTridiagonalMatrixAlgorithm2() {
        TridiagonalMatrixAlgorithm algorithm = new TridiagonalMatrixAlgorithm();
        Assertions.assertArrayEquals(result3, algorithm.solve(matrix3, y3), DELTA);
    }

}