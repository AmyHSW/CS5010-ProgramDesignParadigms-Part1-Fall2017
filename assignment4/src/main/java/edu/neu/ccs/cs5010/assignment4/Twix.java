package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Twix</code> class represents the Twix candy.
 *
 * @author Shuwan Huang
 */
public class Twix extends Candy {

  private static final String TYPE = "Twix";

  /**
   * Constructs a new Twix with the specified size.
   * @param size the size of candy.
   */
  public Twix(Size size) {
    super(size);
  }

  /**
   * Returns a string representation of Twix.
   * @return a string representation of Twix.
   */
  @Override
  public String getType() {
    return TYPE;
  }

}
