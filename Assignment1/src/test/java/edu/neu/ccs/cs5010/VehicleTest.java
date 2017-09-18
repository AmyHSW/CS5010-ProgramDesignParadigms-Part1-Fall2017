package edu.neu.ccs.cs5010;

import edu.neu.ccs.cs5010.Vehicle;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <code>VehicleTest</code> is a test of the <code>Vehicle</code> class.
 *
 * @see edu.neu.ccs.cs5010.Vehicle
 * @author Shuwan Huang
 */
public class VehicleTest {

    private static Vehicle car = null;

    @BeforeClass
    public static void setUp() throws Exception {
        car = new Vehicle(-35, 1);
    }

    /**
     * Tests that getVelocity() returns correct velocity.
     * @throws Exception
     */
    @Test
    public void getVelocity() throws Exception {
        assertEquals("getVelocity()", 35, car.getVelocity(), 0);
    }

    /**
     * Tests that getDirection() returns correct direction.
     * @throws Exception
     */
    @Test
    public void getDirection() throws Exception {
        assertEquals("getDirection()", 2, car.getDirection(), 0);
    }

    /**
     * Tests that setVelocity() correctly sets velocity as the given number.
     * @throws Exception
     */
    @Test
    public void setVelocity() throws Exception {
        Vehicle v = new Vehicle(25, 1);
        v.setVelocity(45);
        assertEquals("setVelocity(45)", 45, v.getVelocity(), 0);
    }

    /**
     * Tests that Vehicle throws an IllegalArgumentException for no inputs given to constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionForNoInputs() { Vehicle v = new Vehicle(); }

    /**
     * Tests that Vehicle throws an IllegalArgumentException for invalid direction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionForInvalidDirection() {
        Vehicle v = new Vehicle(35, 0);
    }

}