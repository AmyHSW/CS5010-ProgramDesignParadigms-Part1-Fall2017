package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>BabyRuth</code> class represents the baby ruth candy.
 *
 * @author Shuwan Huang
 */
public class BabyRuth extends Candy {

  private static final String TYPE = "Baby Ruth";

  /**
   * Constructs a new Baby Ruth with the specified size.
   * @param size the size of candy.
   */
  public BabyRuth(Size size) {
    super(size);
  }

  /**
   * Returns a string representation of Baby Ruth.
   * @return a string representation of Baby Ruth.
   */
  @Override
  public String getType() {
    return TYPE;
  }


}
