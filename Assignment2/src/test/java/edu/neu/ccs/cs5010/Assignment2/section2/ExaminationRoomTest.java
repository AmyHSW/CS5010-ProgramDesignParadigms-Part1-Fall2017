package edu.neu.ccs.cs5010.Assignment2.section2;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.*;

/**
 * The <code>ExaminationRoomTest</code> is a test class for <code>ExaminationRoom</code>.
 *
 * @author Shuwan Huang
 */
public class ExaminationRoomTest {

    private ExaminationRoom room = null;

    /**
     * Creates a new ExaminationRoom object before each test.
     */
    @Before
    public void setUp() throws Exception {
        room = new ExaminationRoom();
    }

    /**
     * Tests that ExaminationRoom adds busy time as provided and return correct busy time.
     */
    @Test
    public void testBusyTime() throws Exception {
        Duration busyTime = Duration.ofMinutes(50);
        room.addBusyTime(busyTime);
        assertTrue("getBusyTime does not return the correct value", room.getBusyTime().equals(busyTime));
    }

    /**
     * Tests that ExaminationRoom throws IllegalArgumentException when null is passed as busy time.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIllegalArgumentExceptionForNullBusyTime() {
        room.addBusyTime(null);
    }

    /**
     * Tests that examination rooms are compared by their busy time as natural ordering.
     */
    @Test
    public void testCompareTo() throws Exception {
        ExaminationRoom other = new ExaminationRoom();
        other.addBusyTime(Duration.ofMinutes(20));
        assertTrue("examination rooms are not compared by busy time", room.compareTo(other) < 0);
    }

    /**
     * Tests that toString returns correct message.
     */
    @Test
    public void testToString() throws Exception {
        Duration busyTime = Duration.ofMinutes(30);
        room.addBusyTime(busyTime);
        String msg = "Examination room (busy for " + busyTime.toMinutes() + " min"
                      + ", treated " + 1 + " patients)";
        assertTrue("toString does not return the correct message", msg.equals(room.toString()));
    }

}