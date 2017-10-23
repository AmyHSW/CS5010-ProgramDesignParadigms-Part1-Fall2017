package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Whoopers</code> class represents the Whoopers candy.
 *
 * @author Shuwan Huang
 */
public class Whoopers extends Candy {

  private static final String TYPE = "Whoopers";

  /**
   * Constructs a new Whoopers with the specified size.
   * @param size the size of candy.
   */
  public Whoopers(Size size) {
    super(size);
  }

  /**
   * Returns a string representation of Whoopers.
   * @return a string representation of Whoopers.
   */
  @Override
  public String getType() {
    return TYPE;
  }

}
