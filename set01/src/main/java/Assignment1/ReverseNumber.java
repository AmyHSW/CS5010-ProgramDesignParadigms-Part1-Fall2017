package Assignment1;
import java.util.Scanner;

/**
 * The <code>ReverseNumber</code> class provides a client for reading in an integer value and printing out its reserved number.
 *
 * @author Shuwan Huang
 */
public class ReverseNumber {

    /**
     * Reads in an integer from a String using a Scanner object and computes its reverse.
     * If the integer is negative, return its negative reverse.
     *
     * @param str a String that contains one integer number
     * @return the reverse of the integer contained in <var>str</var>
     * @throws IllegalArgumentException() if <var>str</var> is null
     * @throws IllegalArgumentException() if no integer in <var>str</var>
     */
    public int getReverseNumber(String str) {
        if (str == null) {
            throw new IllegalArgumentException("No input number is given");
        }
        Scanner sc = new Scanner(str);
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
     * Reads one command-line integer; creates a <code>ReverseNumber</code> object;
     * computes the reverse of that integer; and prints it to console.
     *
     * @param args the command-line arguments
     * @throws IllegalArgumentException() if args length is greater than 1
     */
    public static void main(String[] args) {
        if (args.length > 1) {
            throw new IllegalArgumentException("Please give only one integer.");
        }
        ReverseNumber rn = new ReverseNumber();
        System.out.println(rn.getReverseNumber(args[0]));
    }
}
