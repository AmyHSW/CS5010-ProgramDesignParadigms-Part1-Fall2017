package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>INeighborhoodTraversal</code> interface represents a neighborhood traversal.
 *
 * @author Shuwan Huang
 */
public interface INeighborhoodTraversal {

  /**
   * Returns true if there exists an achievable neighborhood traversal.
   * @return true if there exists an achievable neighborhood traversal, false otherwise
   */
  boolean hasTraversal();

  /**
   * Returns the traversal if there exists one, otherwise returns null.
   * @return the traversal if there exists one, otherwise returns null.
   */
  String getTraversal();
}
