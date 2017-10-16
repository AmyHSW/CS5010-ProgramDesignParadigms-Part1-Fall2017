package edu.neu.ccs.cs5010.assignment3;

import org.junit.Test;

import javax.mail.MessagingException;

/**
 * The <code>EmailAutomationToolTest</code> is a test class for <code>EmailAutomationTool</code>.
 *
 * @author Shuwan Huang
 */
public class EmailAutomationToolTest {

  /**
   * Tests that main method accepts command-line arguments as the input to EmailAutomationTool.
   */
  @Test
  public void main() throws Exception {
    String[] args = {
        "--email-template", "email-template.txt",
        "--output-dir", "emails",
        "--csv-file", "Flight3FromVancouverToSeattle.csv",
        "--event", "arrival",
    };
    EmailAutomationTool.main(args);

    args = new String[] {
        "--email-template", "email-template.txt",
        "--output-dir", "emails",
        "--csv-file", "Flight3FromVancouverToSeattle.csv",
        "--event", "arrival",
        "--mode", "1"
    };
    EmailAutomationTool.main(args);
  }

  /**
   * Tests the EmailAutomationTool throws a MessagingException when provided invalid email address
   * and password.
   *
   * @throws MessagingException if email address or password is invalid
   */
  @Test(expected = MessagingException.class)
  public void testThrowsMessagingException() throws Exception {
    String[] argsOptional = {
        "--email-template", "email-template.txt",
        "--output-dir", "emails",
        "--csv-file", "Flight3FromVancouverToSeattle.csv",
        "--event", "arrival",
        "--mode", "2",
        "--from-email", "user@gmail.com",
        "--password", "password"
    };
    EmailAutomationTool.main(argsOptional);
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
    EmailAutomationTool.main(args1);
  }

}