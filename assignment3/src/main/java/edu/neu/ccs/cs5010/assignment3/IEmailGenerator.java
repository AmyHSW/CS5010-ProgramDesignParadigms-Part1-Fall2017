package edu.neu.ccs.cs5010.assignment3;

/**
 * The <code>IEmailGenerator</code> interface represents an email generator.
 *
 * @author Shuwan Huang
 */
public interface IEmailGenerator {

    /**
     * Returns a string that contains the email to the member
     * @param member IMember object
     * @return a string that contains the email to the member
     */
    String getEmail(IMember member);

}
