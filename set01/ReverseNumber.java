package Assignment1;
import java.util.Scanner;

/**
 * The <code>ReverseNumber</code> class provides a client for reading in an integer value and printing out its reserved number.
 *
 * @author Shuwan Huang
 */
public class ReverseNumber {

    /**
     * @param n the integer to be reversed
     * @return the reversed number
     */
    public int getReversedOf(int n) {
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
     * Reads in an integer number from system input and prints out its reversed number to console.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReverseNumber rn = new ReverseNumber();

        // read integer and print the reversed number
        while (sc.hasNextInt()) {
            System.out.println(rn.getReversedOf(sc.nextInt()));
        }
    }
}
