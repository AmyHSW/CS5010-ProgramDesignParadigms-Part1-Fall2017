package edu.neu.ccs.cs5010.assignment2.section2;

/**
 * Thrown to indicate that startExamination method in Patient has been passed a startTime that is
 * earlier than arrival time of patient.
 *
 * @author Shuwan Huang
 */
public class InvalidStartTimeException extends RuntimeException {

  /**
   * Constructs an InvalidStartTimeException with the specified detail message.
   *
   * @param msg the detail message.
   */
  public InvalidStartTimeException(String msg) {
    super(msg);
  }

}
