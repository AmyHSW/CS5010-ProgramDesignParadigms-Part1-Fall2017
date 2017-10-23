package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Snickers</code> class represents the Snickers candy.
 *
 * @author Shuwan Huang
 */
public class Snickers extends Candy {

  private static final String TYPE = "Snickers";

  /**
   * Constructs a new Snickers with the specified size.
   * @param size the size of candy.
   */
  public Snickers(Size size) {
    super(size);
  }

  /**
   * Returns a string representation of Snickers.
   * @return a string representation of Snickers.
   */
  @Override
  public String getType() {
    return TYPE;
  }

}
