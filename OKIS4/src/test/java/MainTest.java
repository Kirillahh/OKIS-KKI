package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static org.example.Main.*;

public class MainTest {

    // Группы тестов для логической категоризации
    private static final String ARITHMETIC_GROUP = "arithmetic";
    private static final String EXCEPTION_GROUP = "exception";
    private static final String VALIDATION_GROUP = "validation";

    // --- ТЕСТЫ БЕЗ ИСКЛЮЧЕНИЙ ---

    @Test(groups = {ARITHMETIC_GROUP})
    public void testSum() {
        // ARRANGE
        final int a = 5;
        final int b = 7;
        final int expected = 12;
        // ACT
        int actual = sum(a, b);
        // ASSERT
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = {ARITHMETIC_GROUP})
    public void testSubtract() {
        final int a = 10;
        final int b = 4;
        final int expected = 6;
        int actual = subtract(a, b);
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = {VALIDATION_GROUP})
    public void testIsPositiveWithPositiveNumber() {
        Assert.assertTrue(isPositive(10));
    }

    @Test(groups = {VALIDATION_GROUP})
    public void testIsPositiveWithNegativeNumber() {
        Assert.assertFalse(isPositive(-5));
    }

    @Test(groups = {VALIDATION_GROUP})
    public void testIsPositiveWithZero() {
        Assert.assertFalse(isPositive(0));
    }

    // --- ТЕСТЫ С ИСПОЛЬЗОВАНИЕМ DATA PROVIDER ---

    @DataProvider(name = "divisionData")
    public Object[][] divisionData() {
        return new Object[][]{
                {10, 2, 5},
                {15, 3, 5},
                {7, 1, 7},
                {0, 5, 0}
        };
    }

    @Test(groups = {ARITHMETIC_GROUP}, dataProvider = "divisionData")
    public void testDivisionWithDataProvider(int a, int b, int expected) {
        int actual = divide(a, b);
        Assert.assertEquals(actual, expected);
    }

    // --- ТЕСТЫ НА ИСКЛЮЧЕНИЯ (должно быть не менее 2) ---

    @Test(groups = {EXCEPTION_GROUP},
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Divisor cannot be zero")
    public void testDivisionByZeroThrowsException() {
        divide(10, 0); // Должно выбросить IllegalArgumentException
    }

    @Test(groups = {EXCEPTION_GROUP},
            expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void testGetElementAtIndexOutOfBounds() {
        int[] array = {1, 2, 3};
        getElementAtIndex(array, 5); // Должно выбросить ArrayIndexOutOfBoundsException
    }

    // --- ЕЩЕ ОДИН ТЕСТ ДЛЯ ПОЛНОТЫ (8-й тест) ---

    @Test(groups = {ARITHMETIC_GROUP})
    public void testGetElementAtIndexValid() {
        int[] array = {1, 2, 3};
        int actual = getElementAtIndex(array, 1);
        Assert.assertEquals(actual, 2);
    }
}