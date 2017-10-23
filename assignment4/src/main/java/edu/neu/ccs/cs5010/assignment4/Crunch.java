package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Crunch</code> class represents the Crunch candy.
 *
 * @author Shuwan Huang
 */
public class Crunch extends Candy {

  private static final String TYPE = "Crunch";

  /**
   * Constructs a new Crunch with the specified size.
   * @param size the size of candy
   */
  public Crunch(Size size) {
    super(size);
  }

  /**
   * Returns a string representation of Crunch.
   * @return a string representation of Crunch.
   */
  @Override
  public String getType() {
    return TYPE;
  }

}
