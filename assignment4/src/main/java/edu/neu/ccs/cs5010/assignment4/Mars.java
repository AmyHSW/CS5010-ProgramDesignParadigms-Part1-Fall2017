package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Mars</code> class represents the Mars candy.
 *
 * @author Shuwan Huang
 */
public class Mars extends Candy {

  private static final String TYPE = "Mars";

  /**
   * Constructs a new Mars with the specified size.
   * @param size the size of candy
   */
  public Mars(Size size) {
    super(size);
  }

  /**
   * Returns a string representation of Mars.
   * @return a string representation of Mars.
   */
  @Override
  public String getType() {
    return TYPE;
  }

}
