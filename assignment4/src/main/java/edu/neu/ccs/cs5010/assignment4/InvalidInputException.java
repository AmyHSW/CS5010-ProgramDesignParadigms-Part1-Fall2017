package edu.neu.ccs.cs5010.assignment4;

/**
 * Thrown to indicate that the user input is invalid.
 *
 * @author Shuwan Huang
 */
public class InvalidInputException extends RuntimeException {

  /**
   * Constructs an InvalidInputException with the detailed error message.
   * @param msg the detail message
   */
  public InvalidInputException(String msg) {
    super(msg);
  }
}
