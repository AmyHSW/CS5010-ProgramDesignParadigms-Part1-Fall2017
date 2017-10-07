package edu.neu.ccs.cs5010.assignment3;

import org.junit.Before;
import org.junit.Test;

/**
 * The <code>EmailAutomationToolTest</code> is a test class for <code>EmailAutomationTool</code>.
 *
 * @author Shuwan Huang
 */
public class EmailAutomationToolTest {

    private IEmailAutomationTool automation = null;
    private String[] args;

    /**
     * Initializes a new EmailAutomationTool before each test.
     */
    @Before
    public void setUp() throws Exception {
        args = new String[] {
                "--email-template", "email-template.txt",
                "--output-dir", "emails",
                "--csv-file", "Flight3FromVancouverToSeattle.csv",
                "--event", "arrival"
        };
        automation = new EmailAutomationTool(args);
    }

    @Test
    public void startEmailAutomation() throws Exception {
        automation.startEmailAutomation();
    }

    @Test
    public void main() throws Exception {
        EmailAutomationTool.main(args);
    }

    /**
     * Tests that EmailAutomationTool throws an InvalidInputFormatException if the arguments are in wrong format.
     */
    @Test(expected = InvalidInputFormatException.class)
    public void testThrowsInvalidInputFormatException() throws Exception {
        String[] args1 = {
                "--email-template",
                "--output-dir", "emails",
                "--csv-file", "Flight363FromSeattleToBoston.csv",
                "--event", "arrival"
        };
        new EmailAutomationTool(args1);
    }

}