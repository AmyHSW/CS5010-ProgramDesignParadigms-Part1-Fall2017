package edu.neu.ccs.cs5010.assignment3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <code>KeyValueEvaluator</code> is an evaluator that returns the value
 * if the given placeholder can be found.
 *
 * @author Shuwan Huang
 */
public class KeyValueEvaluator implements Evaluator {

  private final Map<String, String> map = new HashMap<>();

  /**
   * Constructs a new KeyValueEvaluator with the placeholder and corresponding argument.
   * @param placeholder a string
   * @param argument a string
   */
  public KeyValueEvaluator(String placeholder, String argument) {
    map.put(placeholder, argument);
  }

  /**
   * Constructs a new KeyValueEvaluator with headers and corresponding passenger information.
   * @param headers a list of strings
   * @param info s list of strings
   */
  public KeyValueEvaluator(List<String> headers, List<String> info) {
    for (int i = 0; i < headers.size(); i++) {
      map.put(headers.get(i), info.get(i));
    }
  }

  /**
   * Returns the value to the corresponding placeholder.
   * @param placeholder a string
   * @return the value to the corresponding placeholder, null if the placeholder is not found.
   */
  @Override
  public String getValue(String placeholder) {
    return map.get(placeholder);
  }
}
