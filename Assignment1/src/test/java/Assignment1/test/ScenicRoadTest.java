package Assignment1.test;

import Assignment1.ScenicRoad;
import Assignment1.Vehicle;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * <code>ScenicRoadTest</code> is a test of <code>ScenicRoad</code> class.
 *
 * @see Assignment1.ScenicRoad
 * @author Shuwan Huang
 */
public class ScenicRoadTest {

    private static ScenicRoad sr = null;
    private static Vehicle v1 = null;
    private static Vehicle v2 = null;

    @BeforeClass
    public static void setUp() throws Exception {
        sr = new ScenicRoad(5);
        v1 = new Vehicle(35, 1);
        v2 = new Vehicle(30, 2);
    }

    /**
     * Tests that add(Vehicle v) throws an IllegalArgumentException for a null input.
     */
    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionForAddNull() { sr.add(null); }

    /**
     * Tests that remove(Vehicle v) throws an IllegalArgumentException for a null input.
     */
    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionForRemoveNull() { sr.remove(null); }

    /**
     * Tests that iterator() returns an Iterator.
     * @throws Exception
     */
    @Test
    public void iterator() throws Exception { Iterator it = sr.iterator(); }

    /**
     * Tests that add and remove Vehicles return correct results,
     * tests that contains() returns correct result,
     * tests that number of Vehicles in each direction is correctly returned,
     * tests that velocity is correctly set for both traffic and non-traffic cases.
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        for (int i = 0; i < 5; i++) {
            sr.add(new Vehicle(35, 1));
        }
        for (int i = 0; i < 3; i++) {
            sr.add(new Vehicle(30, 2));
        }
        assertTrue("add(v1)", sr.add(v1));
        assertTrue("add(v2)", sr.add(v2));
        assertFalse("add(v1)", sr.add(v1));

        assertTrue("remove(v2)", sr.remove(v2));

        assertFalse("contains(v2)", sr.contains(v2));

        assertEquals("numberVehiclesEastbound()", 6, sr.numberVehiclesEastbound(), 0);
        assertEquals("numberVehiclesWestbound()", 3, sr.numberVehiclesWestbound(), 0);

        assertEquals("v1.getVelocity()", 5, v1.getVelocity(), 0);
        assertEquals("getVelocityEastbound()", 5, sr.getVelocityEastbound(), 0);
        assertEquals("getVelocityWestbound()", 30, sr.getVelocityWestbound(), 0);
    }

}