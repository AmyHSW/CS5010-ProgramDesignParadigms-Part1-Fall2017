package edu.neu.ccs.cs5010;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The <code>ReverseNumber</code> class provides a client for reading in an integer value and printing out its reserved number.
 *
 * @author Shuwan Huang
 */
public class ReverseNumber {

    /**
     * Reads in an integer, computes its reverse and returns the reverse.
     * If the integer is negative, return its negative reverse.
     *
     * @param n an integer to be reversed.
     * @return the reverse of n.
     */
    public static long getReverseNumber(int n) {
        if (n < 0) {
            return -reverse(-n);
        } else {
            return reverse(n);
        }
    }

    // a helper method to compute the reversed number of a positive integer.
    private static long reverse(int n) {
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n = n / 10;
        }
        return rev;
    }

    /**
     * Reads in an integer from system input using a Scanner object, and prints the reverse of
     * that integer to console.
     *
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        ReverseNumber rn = new ReverseNumber();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Please enter an integer: ");
            System.out.println("The reverse number is " + rn.getReverseNumber(sc.nextInt()));
        } catch (InputMismatchException e) {
            System.out.println("This is not an integer.");
        }
    }
}
