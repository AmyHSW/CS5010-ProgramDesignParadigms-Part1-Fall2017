package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Candy</code> abstract class abstracts out common fields of candies.
 *
 * @author Shuwan Huang
 */
public abstract class Candy implements ICandy {

  private final Size size;

  /**
   * Constructs a new candy with the specified size.
   * @param size the size of candy.
   */
  public Candy(Size size) {
    this.size = size;
  }

  /**
   * Returns the size of candy.
   * @return the size of candy.
   */
  public Size getSize() {
    return size;
  }

  /**
   * Returns a string representation of type of candy.
   * @return a string representation of type of candy.
   */
  public String getType() {
    return null;
  }

  /**
   * Returns true if this candy is the one that visitor is looking for.
   * @param visitor a GenericVisitor object
   * @return true if this candy is the one visitor is looking for, false otherwise.
   */
  @Override
  public boolean accept(GenericVisitor<Boolean> visitor) {
    return visitor.visit(this);
  }
}
