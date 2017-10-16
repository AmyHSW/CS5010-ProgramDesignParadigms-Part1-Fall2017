package edu.neu.ccs.cs5010.assignment3;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The <code>EvaluatorContainer</code> is a container of evaluators, itself is also an evaluator.
 *
 * @author Shuwan Huang
 */
public class EvaluatorContainer implements IEvaluatorContainer {

  private Deque<Evaluator> evaluators = new ArrayDeque<>();

  /**
   * Returns the value if the placeholder has values in one of the evaluators
   * @param placeholder a string
   * @return the value, or null if placeholder is not found
   */
  @Override
  public String getValue(String placeholder) {
    for (Evaluator evaluator : evaluators) {
      if (evaluator.getValue(placeholder) != null) {
        return evaluator.getValue(placeholder);
      }
    }
    return null;
  }

  /**
   * Adds an evaluator to this evaluator container.
   * @param evaluator an evaluator
   */
  @Override
  public void push(Evaluator evaluator) {
    evaluators.push(evaluator);
  }

  /**
   * Removes the evaluator that was most recently added.
   */
  @Override
  public void pop() {
    if (!evaluators.isEmpty()) evaluators.pop();
  }
}
