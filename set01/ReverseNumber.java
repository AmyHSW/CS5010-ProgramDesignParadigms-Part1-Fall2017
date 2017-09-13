package Assignment1;
import java.util.Scanner;

/**
 * The <code>ReverseNumber</code> class provides a client for reading in an integer value and printing out its reserved number.
 *
 * @author Shuwan Huang
 */
public class ReverseNumber {

    /**
     * Reads in an integer using a Scanner object and computes its reverse.
     * If the integer is negative, return its negative reverse.
     *
     * @param sc a Scanner object that reads in an integer
     * @return the reverse of the integer read by <var>sc</var>
     */
    public int getReverseNumber(Scanner sc) {
        if (!sc.hasNextLine()) {
            throw new IllegalArgumentException("No input number is given.");
        }
        if (!sc.hasNextInt()) {
            throw new IllegalArgumentException("The input is not an integer.");
        }
        int n = sc.nextInt();
        if (n < 0) {
            return -reverse(-n);
        } else {
            return reverse(n);
        }
    }

    // a helper method to compute the reversed number of a positive integer
    private int reverse(int n) {
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n = n / 10;
        }
        return rev;
    }

    /**
     * Reads in an integer from system input using a Scanner object and prints the reversed number to console.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        ReverseNumber rn = new ReverseNumber();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println(rn.getReverseNumber(sc));
        }
    }
}
