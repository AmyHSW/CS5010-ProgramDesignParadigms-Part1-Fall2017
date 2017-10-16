package edu.neu.ccs.cs5010.assignment3;

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
