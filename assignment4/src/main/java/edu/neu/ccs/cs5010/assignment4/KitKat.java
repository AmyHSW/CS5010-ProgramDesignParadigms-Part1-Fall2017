package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>KitKat</code> class represents the Kit Kat candy.
 *
 * @author Shuwan Huang
 */
public class KitKat extends Candy {

  private static final String TYPE = "Kit Kat";

  /**
   * Constructs a new Kit Kat with the specified size.
   * @param size the size of candy.
   */
  public KitKat(Size size) {
    super(size);
  }

  /**
   * Returns a string representation of Kit Kat.
   * @return a string representation of Kit Kat.
   */
  @Override
  public String getType() {
    return TYPE;
  }

}
