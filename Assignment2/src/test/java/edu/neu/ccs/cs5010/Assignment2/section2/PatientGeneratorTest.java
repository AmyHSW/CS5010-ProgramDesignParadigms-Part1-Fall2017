package edu.neu.ccs.cs5010.assignment2.section2;

import org.junit.Before;
import org.junit.Test;

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
        patientGenerator = new PatientGenerator();
    }

    /**
     * Tests that PatientGenerator generates a new Patient object when next is called, and
     * the patient id increments by 1.
     */
    @Test
    public void testNext() throws Exception {
        Patient patient1 = patientGenerator.next();
        assertTrue("id of first patient should be 1 but it is not", patient1.getID() == 1);

        Patient patient2 = patientGenerator.next();
        assertTrue("id of second patient should be 2 but it is not", patient2.getID() == 2);
    }

}