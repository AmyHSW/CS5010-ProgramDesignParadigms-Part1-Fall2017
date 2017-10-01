package edu.neu.ccs.cs5010.assignment2.section2;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class ERSimulatorTest {

    private ERSimulator simulator = null;

    @Before
    public void setUp() throws Exception {
        simulator = new ERSimulator(1);
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

    @Test
    public void testOnPriorityQueues() throws Exception {
        Patient p1 = new Patient(LocalDateTime.now(), 8, Duration.ofMinutes(1), 1);
        Patient p2 = new Patient(LocalDateTime.now(), 2, Duration.ofMinutes(1), 2);
        Patient p3 = new Patient(LocalDateTime.now(), 1, Duration.ofMinutes(1), 3);
        Patient p4 = new Patient(LocalDateTime.now(), 5, Duration.ofMinutes(1), 4);
        Patient p5 = new Patient(LocalDateTime.now(), 2, Duration.ofMinutes(1), 5);

        simulator.addPatient(p1);
        simulator.addPatient(p2);
        simulator.addPatient(p3);
        simulator.addPatient(p4);
        simulator.addPatient(p5);

        assertFalse("simulator is free when it should be busy", simulator.isFree());

        assertFalse("arrivalQueue is empty but it should not be", simulator.arrivalQueue.isEmpty());
        assertTrue("roomQueue is not empty but it should be", simulator.roomQueue.isEmpty());
        assertFalse("examQueue is empty but it should not be", simulator.examinationQueue.isEmpty());
        assertTrue("simulator should be treating p1 but it is not", simulator.examinationQueue.front().equals(p1));

        List<Patient> patientList = simulator.arrivalQueue.testForwardTraversal();
        assertTrue("there should be 4 patients on the queue", patientList.size() == 4);
        assertTrue("p3 should be 1st priority patient", patientList.get(0).equals(p3));
        assertTrue("p2 should be 2nd priority patient", patientList.get(1).equals(p2));
        assertTrue("p5 should be 3rd priority patient", patientList.get(2).equals(p5));
        assertTrue("p4 should be 4th priority patient", patientList.get(3).equals(p4));

        simulator.update();
    }

    @Test
    public void testReportPatientAvrgWaitAndTreatment() throws Exception {
        simulator.reportPatientsAvrgWaitAndTreatment();
    }

    @Test
    public void testReportRoomUsage() throws Exception {
        simulator.reportRoomUsage(Duration.ofMinutes(1));
    }

}