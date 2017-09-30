package edu.neu.ccs.cs5010.Assignment2.section2;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * The <code>PatientTest</code> is a test class for <code>Patient</code>.
 *
 * @author Shuwan Huang
 */
public class PatientTest {

    private Patient patient = null;
    private LocalDateTime arrival = null;
    private Duration treatTime = null;
    private int urgency = 0;
    private int id = 1;

    /**
     * Creates a new Patient object before each test.
     */
    @Before
    public void setUp() throws Exception {
        arrival = LocalDateTime.of(2017, 9, 28, 16, 30);
        treatTime = Duration.ofMinutes(30);
        urgency = 3;
        patient = new Patient(arrival,urgency, treatTime, id);
    }

    /**
     * Tests that getUrgencyLevel returns the correct urgency level.
     */
    @Test
    public void testGetUrgencyLevel() throws Exception {
        assertEquals("urgency level should be 3 but it isn't", 3, patient.getUrgencyLevel());
    }

    /**
     * Starts examination on the patient and tests if the ExaminationRoom, waitTime, and departureTime
     * are set accordingly and correctly.
     */
    @Test
    public void testExamination() throws Exception {
        ExaminationRoom room = new ExaminationRoom();
        LocalDateTime start = LocalDateTime.of(2017, 9, 28, 18, 0);

        patient.startExamination(room, start);

        assertTrue("getRoom doesn't return the correct ExaminationRoom",
                    room.equals(patient.getRoom()));

        assertTrue("getWaitTime doesn't return the correct wait period",
                    Duration.between(arrival, start).equals(patient.getWaitTime()));

        assertTrue("getDepartureTime doesn't return the correct departure time",
                    start.plus(treatTime).equals(patient.getDepartureTime()));


    }

    /**
     * Tests that patients with equal urgency level are ordered by their arrival time.
     */
    @Test
    public void testCompareToForEqualUrgency() throws Exception {
        Patient other = new Patient(arrival.plusMinutes(20), urgency, treatTime, id);
        assertTrue("patient who arrived earlier should be ordered before the other but it isn't",
                   patient.compareTo(other) < 0);
    }

    /**
     * Tesets that patients are ordered by their urgency levels.
     */
    @Test
    public void testCompareToForEqualArrival() throws Exception {
        Patient other = new Patient(arrival, urgency + 2, treatTime, id + 1);
        assertTrue("patient with lower urgency level should be ordered before the other but it isn't",
                   patient.compareTo(other) < 0);
    }

    /**
     * Tests that Patient throws IllegalArgumentException for invalid urgency level.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIllegalArgumentExceptionForInvalidUrgency() throws Exception {
        Patient other = new Patient(arrival, 0, treatTime, id + 1);
    }

    /**
     * Tests that toString returns the correct message.
     */
    @Test
    public void testToString() throws Exception {
        String msg = "Patient (ID-" + id
                      + "): Arrived at " + arrival
                      + ", urgency level is " + urgency
                      +", treatment time is " + treatTime.toMinutes() + " min.";
        assertTrue("toString doesn't provide the correct message", msg.equals(patient.toString()));
    }

}