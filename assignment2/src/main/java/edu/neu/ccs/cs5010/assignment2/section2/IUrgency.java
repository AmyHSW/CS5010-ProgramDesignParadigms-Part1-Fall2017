package edu.neu.ccs.cs5010.assignment2.section2;

/**
 * The <code>IUrgency</code> interface represents the urgency of patients.
 *
 * @author Shuwan Huang
 */
public interface IUrgency extends Comparable<IUrgency> {

  /**
   * Returns the urgency level of this urgency
   * @return the urgency level
   */
  int getLevel();
}
