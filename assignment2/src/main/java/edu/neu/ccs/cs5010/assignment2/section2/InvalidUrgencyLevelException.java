package edu.neu.ccs.cs5010.assignment2.section2;

/**
 * Thrown to indicate that Patient constructor has been passed an invalid urgency level.
 *
 * @author Shuwan Huang
 */
public class InvalidUrgencyLevelException extends RuntimeException {

  /**
   * Constructs an InvalidUrgencyLevelException with the specified detail message.
   *
   * @param msg the detail message.
   */
  public InvalidUrgencyLevelException(String msg) {
    super(msg);
  }

}
