import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexDoubleTest {

    private static final double DELTA = 1E-4d;

    private ComplexDouble complex1;
    private ComplexDouble complex2;
    private ComplexDouble complex3;
    private ComplexDouble complex4;

    private void assertEqualsComplex(ComplexDouble actualSubtraction, ComplexDouble expectedSubtractionResult2) {
        assertEquals(expectedSubtractionResult2.real(), actualSubtraction.real(), DELTA);
        assertEquals(expectedSubtractionResult2.real(), actualSubtraction.real(), DELTA);
    }

    @BeforeEach
    void prepareScenario() {
        complex1 = new ComplexDouble( 2,  3);
        complex2 = new ComplexDouble( 5, -1);
        complex3 = new ComplexDouble( 0, -1);
        complex4 = new ComplexDouble( 6,  0);

    }


    @Test
    void testSum1() {
        ComplexDouble expectedSumResult1 = new ComplexDouble(7, 2);
        ComplexDouble actualSum = complex1.sum(complex2);

        assertEqualsComplex(actualSum, expectedSumResult1);
    }

    @Test
    void testSum2() {
        ComplexDouble expectedSumResult2 = new ComplexDouble(6, -1);
        ComplexDouble actualSum = complex3.sum(complex4);

        assertEqualsComplex(actualSum, expectedSumResult2);
    }

    @Test
    void testSubtraction1() {
        ComplexDouble expectedSubtractionResult1 = new ComplexDouble(-3, 4);
        ComplexDouble actualSubtraction = complex1.sub(complex2);

        assertEqualsComplex(actualSubtraction, expectedSubtractionResult1);
    }

    @Test
    void testSubtraction2() {
        ComplexDouble expectedSubtractionResult2 = new ComplexDouble(-6, -1);
        ComplexDouble actualSubtraction = complex3.sub(complex4);

        assertEqualsComplex(actualSubtraction, expectedSubtractionResult2);
    }

    @Test
    void testMultiplication1() {
        ComplexDouble expectedMultiplicationResult1 = new ComplexDouble(13, 13);
        ComplexDouble actualMultiplication = complex1.mul(complex2);

        assertEqualsComplex(actualMultiplication, expectedMultiplicationResult1);
    }

    @Test
    void testMultiplication2() {
        ComplexDouble expectedMultiplicationResult2 = new ComplexDouble(-1, 0);
        ComplexDouble actualMultiplication = complex3.mul(complex3);

        assertEqualsComplex(actualMultiplication, expectedMultiplicationResult2);
    }

    @Test
    void testDivision1() {
        ComplexDouble expectedDivisionResult1 = new ComplexDouble(7d / 26d, 17d / 26d);
        ComplexDouble actualDivision = complex1.div(complex2);

        assertEqualsComplex(actualDivision, expectedDivisionResult1);
    }

    @Test
    void testDivision2() {
        ComplexDouble expectedDivisionResult2 = new ComplexDouble(1, 0);
        ComplexDouble actualDivision = complex3.div(complex3);

        assertEqualsComplex(actualDivision, expectedDivisionResult2);
    }

    @Test
    void testPower1_1() {
        ComplexDouble expectedPoweredResult1_1 = new ComplexDouble(-5, 12);
        ComplexDouble actualPowered = complex1.pow(2);

        assertEqualsComplex(actualPowered, expectedPoweredResult1_1);
    }

    @Test
    void testPower1_2() {
        ComplexDouble expectedPoweredResult1_2 = new ComplexDouble(24, -10);
        ComplexDouble actualPowered = complex2.pow(2);

        assertEqualsComplex(actualPowered, expectedPoweredResult1_2);
    }

    @Test
    void testPower2_1() {
        ComplexDouble expectedPoweredResult2_1 = new ComplexDouble(-1, 0);
        ComplexDouble actualPowered = complex3.pow(2);

        assertEqualsComplex(actualPowered, expectedPoweredResult2_1);
    }

    @Test
    void testPower2_2() {
        ComplexDouble expectedPoweredResult2_2 = new ComplexDouble(36, 0);
        ComplexDouble actualPowered = complex4.pow(2);

        assertEqualsComplex(actualPowered, expectedPoweredResult2_2);
    }

    @Test
    void testLength1() {
        double expectedLengthResult1 = 3.60555d;
        double actualLength = complex1.radius();

        assertEquals(expectedLengthResult1, actualLength, DELTA);
    }

    @Test
    void testLength2() {
        double expectedLengthResult2 = 5.09902d;
        double actualLength = complex2.radius();

        assertEquals(expectedLengthResult2, actualLength, DELTA);
    }

    @Test
    void testAngle1() {
        double expectedAngle1 = 56.3099d;
        double actualAngle = Math.toDegrees(complex1.angle());

        assertEquals(expectedAngle1, actualAngle, DELTA);
    }

    @Test
    void testAngle2() {
        double expectedAngle2 = -11.3099d;
        double actualAngle = Math.toDegrees(complex2.angle());

        assertEquals(expectedAngle2, actualAngle, DELTA);
    }

    @Test
    void testConjugate1_1() {
        ComplexDouble expectedConjugationResult1_1 = new ComplexDouble(2, -3);
        ComplexDouble actualConjugation = complex1.conjugate();

        assertEqualsComplex(actualConjugation, expectedConjugationResult1_1);
    }

    @Test
    void testConjugate1_2() {
        ComplexDouble expectedConjugationResult1_2 = new ComplexDouble(5, 1);
        ComplexDouble actualConjugation = complex2.conjugate();

        assertEqualsComplex(actualConjugation, expectedConjugationResult1_2);
    }

    @Test
    void testConjugate2_1() {
        ComplexDouble expectedConjugationResult2_1 = new ComplexDouble(0, 1);
        ComplexDouble actualConjugation = complex3.conjugate();

        assertEqualsComplex(actualConjugation, expectedConjugationResult2_1);
    }

    @Test
    void testConjugate2_2() {
        ComplexDouble expectedConjugationResult2_2 = new ComplexDouble(6, 0);
        ComplexDouble actualConjugation = complex4.conjugate();

        assertEqualsComplex(actualConjugation, expectedConjugationResult2_2);
    }
}