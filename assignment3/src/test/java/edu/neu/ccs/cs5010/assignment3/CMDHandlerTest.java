package edu.neu.ccs.cs5010.assignment3;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * The <code>ArgumentsParserTest</code> is a test class for <code>ArgumentsParser</code>.
 *
 * @author Shuwan Huang
 */
public class CMDHandlerTest {

    private CMDHandler legalArguments1, legalArguments2, illegalArguments1, illegalArguments2;

    /**
     * Initializes four ArgumentsParser with four different String array before each test.
     * Two of them are constructed with legal arguments, the other two are constructed with illegal arguments.
     */
    @Before
    public void setUp() throws Exception {
        String[] args1 = {
                "--email-template", "email-template.txt",
                "--output-dir", "emails",
                "--csv-file", "Flight363FromSeattleToBoston.csv",
                "--event", "arrival"
        };
        String[] args2 = {
                "--csv-file", "Flight363FromSeattleToBoston.csv",
                "--email-template", "email-template.txt",
                "--output-dir", "emails",
                "--event", "arrival",
                "--from-email", "jbutt@gmail.com",
                "--password", "jamesbutt'sPassword"
        };
        String[] args3 = {
                "--email-template", "--output-dir",
                "--csv-file", "Flight363FromSeattleToBoston",
                "--event", "arrival",
                "--from-email", "jbuttgmail.com"
        };
        String[] args4 = {
                "--email-template", "email-template",
                "--output-dir", "emails",
                "--csv-file", "Flight363.csv",
                "--event", "unknown",
                "--output"
        };
        legalArguments1 = new CMDHandler(args1);
        legalArguments2 = new CMDHandler(args2);
        illegalArguments1 = new CMDHandler(args3);
        illegalArguments2 = new CMDHandler(args4);
    }

    /**
     * Tests that isLegalFormat returns correct answer for both legal arguments and illegal arguments.
     */
    @Test
    public void isLegalFormat() throws Exception {
        assertTrue(legalArguments1.isLegalFormat());
        assertTrue(legalArguments2.isLegalFormat());
        assertTrue(!illegalArguments1.isLegalFormat());
        assertTrue(!illegalArguments2.isLegalFormat());
    }

    /**
     * Tests that getErrorMessage returns null for legal arguments, and a String of error message
     * for illegal arguments.
     */
    @Test
    public void getErrorMessage() throws Exception {
        assertTrue(legalArguments1.getErrorMessage() == null);
        assertTrue(legalArguments2.getErrorMessage() == null);
        assertTrue(illegalArguments1.getErrorMessage() != null);
        assertTrue(illegalArguments2.getErrorMessage() != null);

        System.out.println(illegalArguments1.getErrorMessage());
        System.out.println(illegalArguments2.getErrorMessage());
    }

    /**
     * Tests that getEmailTemplate returns the correct filename for legal arguments,
     * and null for illegal arguments.
     */
    @Test
    public void getEmailTemplate() throws Exception {
        assertTrue("legalArguments1 returns wrong email template",
                    legalArguments1.getTemplate().equals("email-template.txt"));
        assertTrue("legalArguments2 returns wrong email template",
                    legalArguments2.getTemplate().equals("email-template.txt"));
        assertTrue(illegalArguments1.getTemplate() == null);
        assertTrue(illegalArguments2.getTemplate() == null);
    }

    /**
     * Tests that getOutputDir returns the correct folder name for legal arguments,
     * and null for illegal arguments.
     */
    @Test
    public void getOutputDir() throws Exception {
        assertTrue("legalArguments1 returns wrong output dir",
                   legalArguments1.getOutputDir().equals("emails"));
        assertTrue("legalArguments2 returns wrong output dir",
                   legalArguments2.getOutputDir().equals("emails"));
        assertTrue(illegalArguments1.getOutputDir() == null);
        assertTrue(illegalArguments2.getOutputDir() == null);
    }

    /**
     * Tests that getCsvFile returns the correct filename for legal arguments,
     * and null for illegal arguments.
     */
    @Test
    public void getCsvFile() throws Exception {
        assertTrue(legalArguments1.getCsvFile().equals("Flight363FromSeattleToBoston.csv"));
        assertTrue(legalArguments2.getCsvFile().equals("Flight363FromSeattleToBoston.csv"));
        assertTrue(illegalArguments1.getCsvFile() == null);
        assertTrue(illegalArguments2.getCsvFile() == null);
    }

    /**
     * Tests that getFromEmail returns the correct email address if it is provided in the
     * arguments, and null if it is not provided or if arguments are in illegal format.
     */
    @Test
    public void getFromEmail() throws Exception {
        assertTrue(legalArguments1.getFromEmail() == null);
        assertTrue(legalArguments2.getFromEmail().equals("jbutt@gmail.com"));
        assertTrue(illegalArguments1.getFromEmail() == null);
        assertTrue(illegalArguments2.getFromEmail() == null);
    }

    /**
     * Tests that getPassword returns the correct password if it is provided in the
     * arguments, and null if it is not provided or if arguments are in illegal format.
     */
    @Test
    public void getPassword() throws Exception {
        assertTrue(legalArguments1.getPassword() == null);
        assertTrue(legalArguments2.getPassword().equals("jamesbutt'sPassword"));
        assertTrue(illegalArguments1.getPassword() == null);
        assertTrue(illegalArguments2.getPassword() == null);
    }


}