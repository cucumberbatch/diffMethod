package math.calculations.series;

import math.Function;
import math.calculations.series.computing.CosineFourierCore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

class FourierTest {

    private final double DELTA = 1E-2;

//    @Test
//    void testFourierCoefficient() {
//        Fourier fourier = new Fourier(new CosineFourierCore());
//        Function function = (x, a) -> x < 0.5 ? 1/(1+x) : x-0.5;
//        double a = 0;
//        double b = 1;
//        double h = 0.1;
//        int length = (int)((b-a)/h);
//        double[] f = new double[length];
//
//        for (int i = 0; i < length; i++) {
//            f[i] = function.value(i * h);
//        }
//
//        System.out.println(Arrays.toString(fourier.transform(f, a, b)));
//
//    }

    @Test
    void testFourierTransforms1() {
        Function function = (x, a) -> x < 0.5 ? 0 : x - 0.5;
        double a = 0;
        double b = 1;
        double h = 0.01;
        int length = (int)((b-a)/h);
        double[] f = new double[length];

        Fourier fourier = new Fourier(new CosineFourierCore(), length);

        for (int i = 0; i < length; i++) {
            f[i] = function.value(i * h);
        }

        double[] result = fourier.inverseTransform(fourier.transform(f, a, b), length, h);

        for (int i = 0; i < length; i++) {
            System.out.println(i + "\t" + f[i] + "\t" + result[i]);
        }

        Assertions.assertArrayEquals(f, result, DELTA);
    }

    @Test
    void testFourierTransforms2() {
        Function function = (x, a) -> x < 0.33 || x > 0.66 ? 0 : 1;
        double a = 0;
        double b = 1;
        double h = 0.01;
        int length = (int)((b-a)/h);
        double[] f = new double[length];

        Fourier fourier = new Fourier(new CosineFourierCore(), length);

        for (int i = 0; i < length; i++) {
            f[i] = function.value(i * h);
        }

        double[] result = fourier.inverseTransform(fourier.transform(f, a, b), length, h);

        for (int i = 0; i < length; i++) {
            System.out.println(i + "\t" + f[i] + "\t" + result[i]);
        }

        Assertions.assertArrayEquals(f, result, DELTA);
    }

    @Test
    void testFourierTransforms3() {
        Function function = (x, a) -> x < 0.33 || x > 0.66 ? 0 : 1;
        double a = 0;
        double b = 10;
        double h = 0.1;
        int length = (int)((b-a)/h);
        double[] f = new double[length];

        Fourier fourier = new Fourier(new CosineFourierCore(), length);

        for (int i = 0; i < length; i++) {
            f[i] = function.value(i * h);
        }

        double[] result = fourier.inverseTransform(fourier.transform(f, a, b), length, h);

        for (int i = 0; i < length; i++) {
            System.out.println(i + "\t" + f[i] + "\t" + result[i]);
        }

        Assertions.assertArrayEquals(f, result, DELTA);
    }

    @Test
    void testFourierTransforms4() {
        Function function = (x, a) -> cos(x);
        double a = 0;
        double b = 2 * PI;
        double h = 0.1;
        int length = (int)((b-a)/h);
        double[] f = new double[length];

        Fourier fourier = new Fourier(new CosineFourierCore(), length);

        for (int i = 0; i < length; i++) {
            f[i] = function.value(i * h);
        }

        double[] result = fourier.inverseTransform(fourier.transform(f, a, b), length, h);

        for (int i = 0; i < length; i++) {
            System.out.println(i + "\t" + f[i] + "\t" + result[i]);
        }

        Assertions.assertArrayEquals(f, result, DELTA);
    }

    @Test
    void testFourierTransforms5() {
        Function function = (x, a) -> 2.0d;
        double a = 0;
        double b = 1;
        double h = 0.01;
        int length = (int)((b-a)/h);
        double[] f = new double[length];

        Fourier fourier = new Fourier(new CosineFourierCore(), length);

        for (int i = 0; i < length; i++) {
            f[i] = function.value(i * h);
        }

        double[] result = fourier.inverseTransform(fourier.transform(f, a, b), length, h);

        for (int i = 0; i < length; i++) {
            System.out.println(i + "\t" + f[i] + "\t" + result[i]);
        }

        Assertions.assertArrayEquals(f, result, DELTA);
    }

    @Test
    void testFourierTransforms6() {
        Function function = (x, a) -> 2.0d;
        double a = 0;
        double b = 1;
        double h = 0.01;
        int length = (int)((b-a)/h);
        double[] f = new double[length];

        Fourier fourier = new Fourier(new SineFourierCore(), length);

        for (int i = 0; i < length; i++) {
            f[i] = function.value(i * h);
        }

        double[] result = fourier.inverseTransform(fourier.transform(f, a, b), length, h);

        for (int i = 0; i < 10; i++) {
            result = fourier.inverseTransform(fourier.transform(result, a, b), length, h);
        }

        for (int i = 0; i < length; i++) {
            System.out.println(i + "\t" + f[i] + "\t" + result[i]);
        }

        Assertions.assertArrayEquals(f, result, DELTA);
    }

    @Test
    void testFourierTransforms7() {
        Function function = (x, a) -> x < 0.5d ? x : x-0.5d;
        double a = 0;
        double b = 1;
        double h = 0.01;
        int length = (int)((b-a)/h);
        double[] f = new double[length];

        Fourier fourier = new Fourier(new CosineFourierCore(), length);

        for (int i = 0; i < length; i++) {
            f[i] = function.value(i * h);
        }

        double[] result = fourier.inverseTransform(fourier.transform(f, a, b), length, h);

        for (int i = 0; i < length; i++) {
            System.out.println(i + "\t" + f[i] + "\t" + result[i]);
        }

        Assertions.assertArrayEquals(f, result, DELTA);
    }

    @Test
    void testFourierTransforms8() {
        Function function = (x, a) -> x < 0.5d ? x : x-0.5d;
        double a = 0;
        double b = 1;
        double h = 0.01;
        int length = (int)((b-a)/h);
        double[] f = new double[length];

        Fourier fourier = new Fourier(new SineFourierCore(), length);

        for (int i = 0; i < length; i++) {
            f[i] = function.value(i * h);
        }

        double[] result = fourier.inverseTransform(fourier.transform(f, a, b), length, h);

        for (int i = 0; i < length; i++) {
            System.out.println(i + "\t" + f[i] + "\t" + result[i]);
        }

        Assertions.assertArrayEquals(f, result, DELTA);
    }

}