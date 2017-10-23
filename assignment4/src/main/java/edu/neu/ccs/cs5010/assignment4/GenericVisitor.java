package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>GenericVisitor</code> interface represents a visitor.
 *
 * @param <T> the generic type of visitor
 * @author Shuwan Huang
 */
public interface GenericVisitor<T> {

  /**
   * Returns certain value if the candidate candy matches the one that visitor is looking for.
   * @param candy a candidate candy
   * @return cartain value iff the candidate candy matches the one that visitor is looking for
   */
  T visit(Candy candy);

}
