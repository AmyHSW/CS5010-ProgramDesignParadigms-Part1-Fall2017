package edu.neu.ccs.cs5010.assignment3;

/**
 * The <code>IEvaluatorContainer</code> interface represents an evaluator container.
 *
 * @author Shuwan Huang
 */
public interface IEvaluatorContainer extends Evaluator {

  /**
   * Adds an evaluator to the evaluator container.
   * @param evaluator an evaluator
   */
  void push(Evaluator evaluator);

  /**
   * Removes the evaluator that was most recently added.
   */
  void pop();
}
