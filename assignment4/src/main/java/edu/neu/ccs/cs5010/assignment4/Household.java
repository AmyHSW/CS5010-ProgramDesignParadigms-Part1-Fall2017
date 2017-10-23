package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Household</code> interface represents a household.
 *
 * @author Shuwan Huang
 */
public interface Household {

  /**
   * Returns true if this household offers the candy specified by the candy name.
   * @param candyName a string representation of candy name
   * @return true if this household offers the candy specified by the candy name.
   */
  boolean hasCandy(String candyName);
}
