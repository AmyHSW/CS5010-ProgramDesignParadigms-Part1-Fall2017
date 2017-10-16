package edu.neu.ccs.cs5010.assignment3;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The <code>DateEvaluator</code> is an <code>Evaluator</code> for date.
 *
 * @author Shuwan Huang
 */
public class DateEvaluator implements Evaluator {

  private final String date;

  /**
   * Constructs a new DateEvaluator. Sets the date as the current date.
   */
  public DateEvaluator() {
    date = (new SimpleDateFormat()).format(Calendar.getInstance().getTime());
  }

  /**
   * If placeholder is "Date", returns the date.
   * @param placeholder a string
   * @return date if placeholder is "Date", null otherwise
   */
  @Override
  public String getValue(String placeholder) {
    if (placeholder.equals("Date")) {
      return date;
    }
    return null;
  }
}
