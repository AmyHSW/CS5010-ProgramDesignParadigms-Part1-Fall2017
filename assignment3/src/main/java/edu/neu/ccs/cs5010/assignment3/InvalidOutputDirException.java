package edu.neu.ccs.cs5010.assignment3;

/**
 * Thrown to indicate that the output directory already exists.
 *
 * @author Shuwan Huang
 */
public class InvalidOutputDirException extends RuntimeException {

  /**
   * Constructs an InvalidOutputDirException with the detail error message.
   * @param msg the detail message.
   */
  public InvalidOutputDirException(String msg) {
    super(msg);
  }
}
