package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>MilkyWay</code> class represents the Milky Way candy.
 *
 * @author Shuwan Huang
 */
public class MilkyWay extends Candy {

  private static final String TYPE = "Milky Way";

  /**
   * Constructs a new Milky Way with the specified size.
   * @param size the size of candy.
   */
  public MilkyWay(Size size) {
    super(size);
  }

  /**
   * Returns a string representation of Milky Way.
   * @return a string representation of Milky Way.
   */
  @Override
  public String getType() {
    return TYPE;
  }

}
