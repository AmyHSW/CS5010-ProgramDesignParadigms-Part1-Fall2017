package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>ICandy2</code> interface represents a candy that implemented using
 * interpreter pattern.
 *
 * @author Shuwan Huang
 */
public interface ICandy2 {

  /**
   * Returns true if the candy name matches the name of this candy.
   * @param candyName a string
   * @return true if the candy name matches the name of this candy.
   */
  boolean typeAndSizeCheck(String candyName);

}
