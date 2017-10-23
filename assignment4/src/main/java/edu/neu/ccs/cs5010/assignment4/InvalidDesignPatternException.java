package edu.neu.ccs.cs5010.assignment4;

/**
 * Thrown to indicate that the choice of design pattern is invalid.
 *
 * @author Shuwan Huang
 */
public class InvalidDesignPatternException extends RuntimeException {

  /**
   * Constructs an InvalidDesignPatternException with the detail error message.
   * @param msg the detail message
   */
  public InvalidDesignPatternException(String msg) {
    super(msg);
  }
}
