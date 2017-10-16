package edu.neu.ccs.cs5010.assignment3;

/**
 * The <code>Evaluator</code> interface represents an evaluator.
 *
 * @author Shuwan Huang
 */
public interface Evaluator {

  /**
   * Returns the value to the corresponding placeholder.
   * @param placeholder a string
   * @return the value
   */
  String getValue(String placeholder);
}
