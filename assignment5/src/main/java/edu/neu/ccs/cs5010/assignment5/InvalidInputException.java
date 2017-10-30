package edu.neu.ccs.cs5010.assignment5;

/**
 * Thrown to indicate that the input arguments are invalid.
 *
 * @author Shuwan Huang
 */
public class InvalidInputException extends RuntimeException {

  /**
   * Constructs an InvalidInputException with the detail message.
   * @param msg the detail message
   */
  public InvalidInputException(String msg) {
    super(msg);
  }
}
