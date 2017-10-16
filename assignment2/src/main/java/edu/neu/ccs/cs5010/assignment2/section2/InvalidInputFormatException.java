package edu.neu.ccs.cs5010.assignment2.section2;

/**
 * Thrown to indicate that the input arguments has invalid format.
 *
 * @author Shuwan Huang
 */
public class InvalidInputFormatException extends RuntimeException {

  /**
   * Constructs an InvalidInputFormatException with the specified detail message.
   *
   * @param msg the detail message
   */
  public InvalidInputFormatException(String msg) {
    super(msg);
  }
}
