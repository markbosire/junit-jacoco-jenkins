package com.example;

/**
 * Utility class for string operations
 */
public class StringUtils {
    
    /**
     * Reverses a string
     * @param input string to reverse
     * @return reversed string
     */
    public String reverse(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder reversed = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed.append(input.charAt(i));
        }
        return reversed.toString();
    }
    
    /**
     * Checks if a string is a palindrome (reads the same forwards and backwards)
     * @param input string to check
     * @return true if palindrome, false otherwise
     */
    public boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }
        String cleaned = input.toLowerCase().replaceAll("[^a-z0-9]", "");
        return cleaned.equals(reverse(cleaned));
    }
    
    /**
     * Counts occurrences of a character in a string
     * @param input string to search in
     * @param c character to count
     * @return number of occurrences
     */
    public int countChar(String input, char c) {
        if (input == null) {
            return 0;
        }
        
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }
}
