package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Toblerone</code> class represents the Toblerone candy.
 *
 * @author Shuwan Huang
 */
public class Toblerone extends Candy {

  private static final String TYPE = "Toblerone";

  /**
   * Constructs a new Toblerone with the specified size.
   * @param size the size of candy.
   */
  public Toblerone(Size size) {
    super(size);
  }

  /**
   * Returns a string representation of Toblerone.
   * @return a string representation of Toblerone.
   */
  @Override
  public String getType() {
    return TYPE;
  }

}
