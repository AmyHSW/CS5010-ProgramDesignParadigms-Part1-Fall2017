package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>ICandy</code> interface represents a candy.
 *
 * @author Shuwan Huang
 */
public interface ICandy {

  /**
   * Returns true if this candy is the one that visitor is looking for.
   * @param visitor a GenericVisitor object
   * @return true if this candy is the one visitor is looking for, false otherwise.
   */
  boolean accept(GenericVisitor<Boolean> visitor);
}
