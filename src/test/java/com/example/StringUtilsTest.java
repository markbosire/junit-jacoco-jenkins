package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("String Utilities Test Suite")
public class StringUtilsTest {
    
    private StringUtils stringUtils;
    
    @BeforeEach
    void setUp() {
        stringUtils = new StringUtils();
    }
    
    @Test
    @DisplayName("String reversal")
    void testReverse() {
        assertEquals("cba", stringUtils.reverse("abc"), "Should reverse 'abc' to 'cba'");
        assertEquals("", stringUtils.reverse(""), "Should reverse empty string to empty string");
        assertEquals("a", stringUtils.reverse("a"), "Should reverse single character to itself");
    }
    
    @Test
    @DisplayName("Null string reversal")
    void testReverseNull() {
        assertNull(stringUtils.reverse(null), "Should return null when input is null");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"radar", "level", "deified", "civic", "madam", "rotor", "kayak", "racecar"})
    @DisplayName("Test valid palindromes")
    void testValidPalindromes(String input) {
        assertTrue(stringUtils.isPalindrome(input), input + " should be a palindrome");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"hello", "world", "java", "programming", "test"})
    @DisplayName("Test invalid palindromes")
    void testInvalidPalindromes(String input) {
        assertFalse(stringUtils.isPalindrome(input), input + " should not be a palindrome");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"A man, a plan, a canal: Panama", "No 'x' in Nixon", "Was it a car or a cat I saw?"})
    @DisplayName("Test palindromes with special characters")
    void testPalindromesWithSpecialChars(String input) {
        assertTrue(stringUtils.isPalindrome(input), 
                  "'" + input + "' should be recognized as a palindrome after cleaning");
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test palindrome edge cases")
    void testPalindromeEdgeCases(String input) {
        if (input == null) {
            assertFalse(stringUtils.isPalindrome(input), "null should not be a palindrome");
        } else {
            assertTrue(stringUtils.isPalindrome(input), "empty string should be a palindrome");
        }
    }
    
    @ParameterizedTest
    @CsvSource({
        "hello, l, 2",
        "mississippi, s, 4",
        "banana, a, 3",
        "test, x, 0",
        "'', a, 0"
    })
    @DisplayName("Test character counting")
    void testCountChar(String input, char c, int expected) {
        assertEquals(expected, stringUtils.countChar(input, c),
                    "Should find " + expected + " occurrences of '" + c + "' in '" + input + "'");
    }
    
    @Test
    @DisplayName("Test counting in null string")
    void testCountCharNull() {
        assertEquals(0, stringUtils.countChar(null, 'a'),
                    "Should return 0 when counting in null string");
    }
}
