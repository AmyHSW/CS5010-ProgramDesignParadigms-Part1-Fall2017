package edu.neu.ccs.cs5010.assignment3;

/**
 * The <code>IEmail</code> interface represents an email.
 *
 * @author Shuwan Huang
 */
public interface IEmail {

  /**
   * Returns the main text of this email.
   * @return the main text of this email.
   */
  String getText();

  /**
   * Returns the email address to which this email will be sent.
   * @return the email address to which this email will be sent.
   */
  String getToEmail();

  /**
   * Returns the entire content of this email.
   * @return the entire content of this email.
   */
  String toString();
}
