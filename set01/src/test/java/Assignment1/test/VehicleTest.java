package Assignment1.test;

import Assignment1.Vehicle;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <code>VehicleTest</code> is a test of the <code>Vehicle</code> class.
 *
 * @see Assignment1.Vehicle
 * @author Shuwan Huang
 */
public class VehicleTest {

    private static Vehicle car = null;

    @BeforeClass
    public static void setUp() throws Exception {
        car = new Vehicle(35, 1);
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
        assertEquals("getDirection()", 1, car.getDirection(), 0);
    }

    /**
     * Tests that setVelocity() correctly sets velocity as the given number.
     * @throws Exception
     */
    @Test
    public void setVelocity() throws Exception {
        Vehicle v = new Vehicle(25, 2);
        v.setVelocity(45);
        assertEquals("setVelocity(45)", 45, v.getVelocity(), 0);
    }

    /**
     * Tests that Vehicle throws an IllegalArgumentException for no input given.
     */
    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionForNoInput() {
        Vehicle v = new Vehicle();
    }

    /**
     * Tests that Vehicle throws an IllegalArgumentException for negative velocity.
     */
    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionForNegativeVelocity() {
        Vehicle v = new Vehicle(-1, 1);
    }

    /**
     * Tests that Vehicle throws an IllegalArgumentException for invalid direction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionForInvalidDirection() {
        Vehicle v = new Vehicle(35, 0);
    }
}