package edu.neu.ccs.cs5010;

import edu.neu.ccs.cs5010.ReverseNumber;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The <code>ReverseNumberTest</code> class is a glassbox test of <code>ReverseNumber</code> class.
 *
 * @see edu.neu.ccs.cs5010.ReverseNumber
 *
 * @author Shuwan Huang
 */
public class ReverseNumberTest {

    private static ReverseNumber rn = null;

    @BeforeClass
    public static void setUp() throws Exception {
        rn = new ReverseNumber();
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
                         cases[i][1], rn.getReverseNumber(cases[i][0]));
        }
    }
}