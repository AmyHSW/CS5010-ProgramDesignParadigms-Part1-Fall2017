package edu.neu.ccs.cs5010.Assignment2.section2;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ERSimulatorTest {

    private ERSimulator simulator = null;

    @Before
    public void setUp() throws Exception {
        simulator = new ERSimulator(2);
    }

    @Test
    public void testIsFreeOnFree() throws Exception {
        assertTrue("isFree returns false for an empty simulator", simulator.isFree());
    }

    @Test
    public void testAddOnePatient() throws Exception {
        simulator.addPatient(new Patient(LocalDateTime.now(), 1, Duration.ofMinutes(20), 1));
    }

    @Test
    public void testIsFreeOnBusy() throws Exception {
        simulator.addPatient(new Patient(LocalDateTime.now(), 1, Duration.ofMinutes(20), 1));
        assertFalse("isFree returns true for a busy simulator", simulator.isFree());
    }

}