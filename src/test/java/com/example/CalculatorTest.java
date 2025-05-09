package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Calculator Test Suite")
public class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Addition of two numbers")
    void testAdd() {
        assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
        assertEquals(0, calculator.add(-2, 2), "-2 + 2 should equal 0");
        assertEquals(-5, calculator.add(-2, -3), "-2 + -3 should equal -5");
    }
    
    @Test
    @DisplayName("Subtraction of two numbers")
    void testSubtract() {
        assertEquals(1, calculator.subtract(3, 2), "3 - 2 should equal 1");
        assertEquals(-1, calculator.subtract(2, 3), "2 - 3 should equal -1");
        assertEquals(0, calculator.subtract(2, 2), "2 - 2 should equal 0");
    }
    
    @Test
    @DisplayName("Multiplication of two numbers")
    void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3), "2 * 3 should equal 6");
        assertEquals(-6, calculator.multiply(2, -3), "2 * -3 should equal -6");
        assertEquals(0, calculator.multiply(0, 3), "0 * 3 should equal 0");
    }
    
    @Test
    @DisplayName("Division of two numbers")
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3), "6 / 3 should equal 2");
        assertEquals(0, calculator.divide(1, 2), "1 / 2 should equal 0 (integer division)");
        assertEquals(-2, calculator.divide(-6, 3), "-6 / 3 should equal -2");
    }
    
    @Test
    @DisplayName("Division by zero throws exception")
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(1, 0);
        });
        
        String expectedMessage = "Cannot divide by zero";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29})
    @DisplayName("Test known prime numbers")
    void testPrimeNumbers(int number) {
        assertTrue(calculator.isPrime(number), number + " should be prime");
    }
    
    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18})
    @DisplayName("Test known non-prime numbers")
    void testNonPrimeNumbers(int number) {
        assertFalse(calculator.isPrime(number), number + " should not be prime");
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, -5, -10})
    @DisplayName("Test edge cases for prime check")
    void testPrimeEdgeCases(int number) {
        assertFalse(calculator.isPrime(number), number + " should not be prime");
    }
    
    @ParameterizedTest
    @CsvSource({
        "6, 3, 2",   // normal division
        "7, 2, 3",   // integer division
        "-6, 3, -2", // negative dividend
        "6, -3, -2", // negative divisor
        "-6, -3, 2"  // both negative
    })
    @DisplayName("Parameterized division test")
    void testParameterizedDivision(int a, int b, int expected) {
        assertEquals(expected, calculator.divide(a, b),
                     a + " / " + b + " should equal " + expected);
    }
}
