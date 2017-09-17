package Assignment1.test;

import Assignment1.Highway;
import Assignment1.Vehicle;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * <code>HighwayTest</code> is a test of <code>Highway</code> class.
 *
 * @see Assignment1.Highway
 * @author Shuwan Huang
 */
public class HighwayTest {

    private static Highway hw = null;
    private static Vehicle car1 = null;
    private static Vehicle car2 = null;

    @BeforeClass
    public static void setUp() throws Exception {
        hw = new Highway();
        car1 = new Vehicle(65, 1);
        car2 = new Vehicle(65, 2);
    }

    /**
     * Tests that iterator() returns an Iterator.
     * @throws Exception
     */
    @Test
    public void iterator() throws Exception {
        Iterator it = hw.iterator();
    }

    @Test
    public void testcase() {
        // tests that add() returns correct results.
        assertTrue("hw.add()", hw.add(new Vehicle(75, 1)));
        assertTrue("hw.add()", hw.add(new Vehicle(80, 1)));
        assertTrue("hw.add()", hw.add(new Vehicle(65, 1)));
        assertTrue("hw.add()", hw.add(new Vehicle(50, 1)));
        assertTrue("hw.add()", hw.add(car1));
        assertFalse("hw.add()", hw.add(car1));

        assertTrue("hw.add()", hw.add(new Vehicle(45, 2)));
        assertTrue("hw.add()", hw.add(new Vehicle(55, 2)));
        assertTrue("hw.add()", hw.add(new Vehicle(60, 2)));
        assertTrue("hw.add()", hw.add(new Vehicle(65, 2)));
        assertTrue("hw.add()", hw.add(new Vehicle(55, 2)));
        assertTrue("hw.add()", hw.add(car2));
        assertFalse("hw.add()", hw.add(car2));

        // tests that remove() returns correct results.
        assertFalse("hw.remove()", hw.remove(new Vehicle(65, 1)));
        assertFalse("hw.remove()", hw.remove(new Vehicle(65, 2)));

        assertTrue("hw.remove()", hw.remove(car1));
        assertTrue("hw.remove()", hw.remove(car2));

        // tests that getVelocityEastBound() returns correct slowest velocity.
        assertEquals("getVelocityEastbound()", 50, hw.getVelocityEastbound(), 0);

        //tests that getVelocityWestBound() returns correct slowest velocity.
        assertEquals("getVelocityWestbound()", 45, hw.getVelocityWestbound(), 0);

        // tests that numberVehiclesEastBound() returns correct number of Vehicles.
        assertEquals("numberVehiclesEastbound()", 4, hw.numberVehiclesEastbound(), 0);

        // tests numberVehiclesWestBound() returns correct number of Vehicles.
        assertEquals("numberVehiclesWestbound()", 5, hw.numberVehiclesWestbound(), 0);

        // tests that contains() returns correct result.
        assertFalse("contains()", hw.contains(new Vehicle(80, 1)));
    }

}