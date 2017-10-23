package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>AlmondJoy</code> class represents the almond joy candy.
 *
 * @author Shuwan Huang
 */
public class AlmondJoy extends Candy {

  private static final String TYPE = "Almond Joy";

  /**
   * Constructs a new almond joy with the specified size.
   * @param size the size of candy.
   */
  public AlmondJoy(Size size) {
    super(size);
  }

  /**
   * Returns a string representation of almond joy.
   * @return a string representation of almond joy.
   */
  @Override
  public String getType() {
    return TYPE;
  }

}
