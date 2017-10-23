package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Mansion2</code> class represents a mansion household implemented using
 * interpreter pattern.
 *
 * @author Shuwan Huang
 */
public class Mansion2 implements Household {

  private static final String TYPE = "Mansion";
  private static final Candy2[] CANDIES = (Candy2[]) new Candy2[] {
      new Twix2(new SuperSize()),
      new Snickers2(new SuperSize()),
      new Mars2(new SuperSize()),
      new KitKat2(new KingSize()),
      new Whoopers2(new KingSize()),
      new Crunch2(new KingSize()),
      new Toblerone2(new FunSize()),
      new BabyRuth2(new FunSize()),
      new AlmondJoy2(new FunSize())
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
   * Returns a string representation of mansion.
   * @return a string representation of mansion.
   */
  @Override
  public String toString() {
    return TYPE;
  }

}
