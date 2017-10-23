package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Townhome2</code> class represents a townhome implemented using
 * interpreter pattern.
 *
 * @author Shuwan Huang
 */
public class Townhome2 implements Household {

  private static final String TYPE = "Townhome";
  private static final Candy2[] CANDIES = (Candy2[]) new Candy2[] {
      new AlmondJoy2(new RegularSize()),
      new BabyRuth2(new RegularSize()),
      new KitKat2(new RegularSize()),
      new Mars2(new RegularSize()),
      new Snickers2(new RegularSize()),
      new Toblerone2(new RegularSize()),
      new Twix2(new RegularSize()),
      new Whoopers2(new RegularSize())
  };

  /**
   * Returns true if this household offers the candy specified by the candy name.
   * @param candyName a string representation of candy name
   * @return true if this household offers the candy specified by the candy name.
   */
  @Override
  public boolean hasCandy(String candyName) {
    for (Candy2 candy : CANDIES) {
      if (candy.typeAndSizeCheck(candyName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns a string representation of town home.
   * @return a string representation of town home.
   */
  @Override
  public String toString() {
    return TYPE;
  }
}
