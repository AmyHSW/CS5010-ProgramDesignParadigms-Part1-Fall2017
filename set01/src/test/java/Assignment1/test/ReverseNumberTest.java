package Assignment1.test;

import Assignment1.ReverseNumber;
import java.util.Scanner;
import org.junit.BeforeClass;
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

    @BeforeClass
    public static void setUp() throws Exception {
        rn = new ReverseNumber();
    }

    /**
     * Tests that ReverseNumber throws an IllegalArgumentException when input is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void expectedIllegalArgumentExceptionForNullInput() {
        rn.getReverseNumber(null);
    }

    /**
     * Tests that ReverseNumber throws an IllegalArgumentException for a character
     */
    @Test(expected = IllegalArgumentException.class)
    public void expectedIllegalArgumentExceptionForCharacter() {
        rn.getReverseNumber("x");
    }

    /**
     * Tests that ReverseNumber throws an IllegalArgumentException for a blank character
     */
    @Test(expected = IllegalArgumentException.class)
    public void expectedIllegalArgumentExceptionForBlank() {
        rn.getReverseNumber(" ");
    }

    /**
     * Tests that ReverseNumber throws an IllegalArgumentException for a float number
     */
    @Test(expected = IllegalArgumentException.class)
    public void expectedIllegalArgumentExceptionForFloatNumber() {
        rn.getReverseNumber("1.0");
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
            assertEquals("getReverseNumber(" + cases[i][0] + ")",
                         cases[i][1], rn.getReverseNumber(Integer.toString(cases[i][0])));
        }
    }
}