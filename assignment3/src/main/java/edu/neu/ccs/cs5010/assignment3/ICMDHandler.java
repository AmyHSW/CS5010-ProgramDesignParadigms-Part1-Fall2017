package edu.neu.ccs.cs5010.assignment3;

/**
 * The <code>ICMDHandler</code> interface represents a cmd handler.
 *
 * @author Shuwan Huang
 */
public interface ICMDHandler {

  /**
   * Returns true if the required information -- email template, output directory,
   * csv file and event detail -- are provided in legal format.
   *
   * @return true if arguments are in legal format and false otherwise.
   */
  boolean isLegalFormat();

  /**
   * Returns the error message if arguments are in illegal format; null if arguments are in
   * legal format.
   *
   * @return the error message if arguments are in illegal format; null if arguments are in
   * legal format.
   */
  String getErrorMessage();

  /**
   * Returns a filename that holds the email template; null if arguments are not in legal format.
   *
   * @return a filename that holds the email template; null if arguments are not in legal format.
   */
  String getTemplate();

  /**
   * Returns the name of a folder that will store all output; null if arguments are
   * not in legal format.
   *
   * @return the name of a folder that will store all output; null if arguments are
   * not in legal format.
   */
  String getOutputDir();

  /**
   * Returns the name of csv file; null if arguments are not in legal format
   *
   * @return the name of csv file; null if arguments are not in legal format
   */
  String getCsvFile();

  /**
   * Returns the event details; null if arguments are not in legal format
   *
   * @return the event details; null if arguments are not in legal format
   */
  String getEvent();

  /**
   * Returns the mode, 1 for saving emails, 2 for sending emails.
   * If user does not provide mode, return the default mode (1).
   *
   * @return Returns the mode, 1 for saving emails, 2 for sending emails.
   */
  int getMode();

  /**
   * Returns the email address from which to send the emails; null if arguments are not
   * in legal format or if email address is not provided.
   *
   * @return the email address from which to send the emails; null if arguments are not
   * in legal format or if email address is not provided.
   */
  String getFromEmail();

  /**
   * Returns the password to the email address; null if arguments are not in legal format or if
   * email address and password are not provided.
   *
   * @return the password to the email address; null if arguments are not in legal format or if
   * email address and password are not provided.
   */
  String getPassword();
}
