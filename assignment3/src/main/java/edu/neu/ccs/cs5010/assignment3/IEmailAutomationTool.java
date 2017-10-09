package edu.neu.ccs.cs5010.assignment3;

import javax.mail.MessagingException;

/**
 * The <code>IEmailAutomationTool</code> interface represents an email automation tool.
 *
 * @author Shuwan Huang
 */
public interface IEmailAutomationTool {

    /**
     * Starts the email automation process. Generates emails for each member and saves them in directed
     * output.
     */
    void startEmailAutomation() throws MessagingException;
}
