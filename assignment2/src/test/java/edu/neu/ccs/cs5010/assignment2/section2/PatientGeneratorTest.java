package edu.neu.ccs.cs5010.assignment2.section2;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * The <code>PatientGeneratorTest</code> is a test class for <code>PatientGenerator</code> class.
 *
 * @author Shuwan Huang
 */
public class PatientGeneratorTest {

    private PatientGenerator patientGenerator= null;

    /**
     * Declares a new PatientGenerator before each test.
     */
    @Before
    public void setUp() throws Exception {
        patientGenerator = new PatientGenerator(1000, 10);
    }

    /**
     * Tests that PatientGenerator generates a new Patient object when next is called, and
     * the patient id increments by 1.
     */
    @Test
    public void testNext() throws Exception {
        patientGenerator.lastTime = LocalDateTime.now().minusHours(1);
        IPatient patient = patientGenerator.next();
        assertTrue(patient != null);
        assertTrue("id of first patient should be 1 but it is not", patient.getID() == 1);

        patientGenerator.lastTime = LocalDateTime.now().minusHours(1);
        patient = patientGenerator.next();
        assertTrue(patient != null);
        assertTrue("id of second patient should be 2 but it is not", patient.getID() == 2);
    }

}