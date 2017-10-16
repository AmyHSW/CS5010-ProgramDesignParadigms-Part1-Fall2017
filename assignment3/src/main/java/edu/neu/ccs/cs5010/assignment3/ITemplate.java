package edu.neu.ccs.cs5010.assignment3;

/**
 * The <code>ITemplate</code> interface represents an email template.
 *
 * @author Shuwan Huang
 */
public interface ITemplate {

  /**
   * Replaces the placeholders by information provided by the Evaluator object.
   *
   * @param evaluator an Evaluator
   * @return a string that contains the email to the passenger
   */
  IEmail toEmail(Evaluator evaluator);
}
