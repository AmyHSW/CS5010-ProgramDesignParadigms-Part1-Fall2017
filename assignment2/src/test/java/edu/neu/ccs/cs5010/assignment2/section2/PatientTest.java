package edu.neu.ccs.cs5010.assignment2.section2;

import jdk.nashorn.internal.runtime.ECMAException;
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
     * Tests that getID returns correct id of patient.
     */
    @Test
    public void testGetID() throws Exception {
        assertEquals("getID does not return the correct id", id, patient.getID());
    }

    /**
     * Tests that getTreatmentDuration returns the correct duration of treatment.
     */
    @Test
    public void testGetTreatmentDuration() throws Exception {
        assertTrue("getTreatmentDuration return wrong output", treatTime.equals(patient.getTreatmentDuration()));
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
                    Duration.between(arrival, start).equals(patient.getWaitDuration()));

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
     * Tests that patients are ordered by their urgency levels.
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
    @Test(expected = InvalidUrgencyLevelException.class)
    public void testThrowsInvalidUrgencyLevelException() throws Exception {
        IPatient other = new Patient(arrival, 0, treatTime, id + 1);
    }

    /**
     * Tests that Patient throws InvalidStartTimeException if start time of examination is earlier than arrival time.
     */
    @Test(expected = InvalidStartTimeException.class)
    public void testThrowsInvalidStartTimeException() throws Exception {
        IExaminationRoom room = new ExaminationRoom();
        patient.startExamination(room, arrival.minusMinutes(20));
    }

    /**
     * Tests that Patient throws IllegalArgumentException for null arrival time.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIllegalArgumentExceptionForNullArrival() throws Exception {
        IPatient other = new Patient(null, urgency, treatTime, id);
    }

    /**
     * Tests that Patient throws IllegalArgumentException for null treatment duration.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIllegalArgumentExceptionForNullTreatment() throws Exception {
        IPatient other = new Patient(arrival, urgency, null, id);
    }

    /**
     * Tests that Patient throws IllegalArgumentException for null examination room.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIllegalArgumentExceptionForNullRoom() throws Exception {
        patient.startExamination(null, LocalDateTime.now());
    }

    /**
     * Tests that Patient throws IllegalArgumentException for null start time.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIllegalArgumentExceptionForNullStart() throws Exception {
        patient.startExamination(new ExaminationRoom(), null);
    }

    /**
     * Tests that equals returns true for patients with the same arrival time, urgency level, treatment time and id.
     */
    @Test
    public void testEquals() throws Exception {
        assertFalse("equals returns true when compare to null", patient.equals(null));

        IPatient other = new Patient(arrival, urgency, treatTime, id);
        assertTrue("equals does not return true for equal patients", patient.equals(other));
    }

    /**
     * Tests that equals returns false for patients with different urgency level.
     */
    @Test
    public void testEqualsOnDifferentUrgency() throws Exception {
        IPatient other = new Patient(arrival, urgency + 1, treatTime, id);
        assertTrue("equals returns true for different patients", !patient.equals(other));
    }

    /**
     * Tests that equals returns false for patients with different arrival time.
     */
    @Test
    public void testEqualsOnDifferentArrival() throws Exception {
        IPatient other = new Patient(arrival.minusMinutes(10), urgency, treatTime, id);
        assertTrue("equals returns true for different patients", !patient.equals(other));
    }

    /**
     * Tests that equals returns false for patients with different treatment time.
     */
    @Test
    public void testEqualsOnDifferentTreatTime() throws Exception {
        IPatient other = new Patient(arrival, urgency, treatTime.plusMinutes(5), id);
        assertTrue("equals returns true for different patients", !patient.equals(other));
    }

    /**
     * Tests that patients that are equal to each other have the same hashcode.
     */
    @Test
    public void testHashCode() throws Exception {
        IPatient other = new Patient(arrival, urgency, treatTime, id);
        assertTrue("equal patients should have the same hashcode",
                   patient.hashCode() == other.hashCode());
    }

    /**
     * Tests that toString returns the correct message.
     */
    @Test
    public void testToString() throws Exception {
        String msg = "Patient (ID-" + id
                      + "): Arrived at " + arrival
                      + ", urgency level is " + urgency
                      +", treatment duration is " + treatTime.toMinutes() + " min.";
        assertTrue("toString doesn't provide the correct message", msg.equals(patient.toString()));
    }

}