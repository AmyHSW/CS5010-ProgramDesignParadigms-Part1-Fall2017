package Assignment1.test;

import Assignment1.Vehicle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleTest {

    private Vehicle car = null;

    @Before
    public void setUp() throws Exception {
        car = new Vehicle(35, 1);
    }

    @Test
    public void getVelocity() throws Exception {
        assertEquals("getVelocity()", 35, car.getVelocity(), 0);
    }

    @Test
    public void getDirection() throws Exception {
        assertEquals("getDirection()", 1, car.getDirection(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionIfNoInput() {
        car = new Vehicle();
    }
}