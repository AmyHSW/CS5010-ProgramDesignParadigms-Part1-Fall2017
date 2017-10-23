package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>ICmdHandler</code> interface represents a CMD handler.
 *
 * @author Shuwan Huang
 */
public interface ICmdHandler {

  /**
   * Returns true if the arguments are valid.
   * @return true if the arguments are valid, false otherwise.
   */
  boolean isValid();

  /**
   * Returns a string array of csv file names.
   * @return a string array of csv file names.
   */
  String[] getCSVFiles();
}
