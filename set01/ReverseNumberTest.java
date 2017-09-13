package Assignment1.test;
import Assignment1.*;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The <code>ReverseNumberTest</code> class is a glassbox test of <code>ReverseNumber</code> class.
 *
 * @see Assignment1.ReverseNumber
 *
 * @author Shuwan Huang
 */
public class ReverseNumberTest {

    private static ReverseNumber rn = null;

    @Before
    public void setUp() throws Exception {
        rn = new ReverseNumber();
    }

    /**
     * Tests that ReverseNumber throws an IllegalArgumentException for a character
     */
    @Test(expected=IllegalArgumentException.class)
    public void expectedExceptionCharacter() {
        String str = "x";
        Scanner sc = new Scanner(str);
        rn.getReverseNumber(sc);
    }

    /**
     * Tests that ReverseNumber throws an IllegalArgumentException when no input is given
     */
    @Test(expected=IllegalArgumentException.class)
    public void expectedExceptionNoInput() {
        String str = "";
        Scanner sc = new Scanner(str);
        rn.getReverseNumber(sc);
    }

    /**
     * Tests that ReverseNumber throws an IllegalArgumentException for a float number
     */
    @Test(expected=IllegalArgumentException.class)
    public void expectedExceptionFloatNumber() {
        String str = "1.0";
        Scanner sc = new Scanner(str);
        rn.getReverseNumber(sc);
    }

    /** Tests to see if ReverseNumber returns the correct reverse values of integer inputs */
    @Test
    public void testCase() {
        int[][] cases = new int[][] {
                {25, 52},
                {379, 973},
                {-10, -1},
                {1, 1},
                {0, 0}
        };
        for (int i = 0; i < cases.length; i++) {
            Scanner sc = new Scanner(Integer.toString(cases[i][0]));
            assertEquals("getReverseNumber(" + cases[i][0] + ")",
                         cases[i][1], rn.getReverseNumber(sc));
        }
    }
}