package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>DetachedHouse2</code> class represents a detached house implemented using interpreter
 * pattern.
 *
 * @author Shuwan Huang
 */
public class DetachedHouse2 implements Household {

  private static final String TYPE = "Detached House";
  private static final Candy2[] CANDIES = (Candy2[]) new Candy2[] {
      new KitKat2(new SuperSize()),
      new Whoopers2(new SuperSize()),
      new MilkyWay2(new SuperSize()),
      new Crunch2(new SuperSize()),
      new Toblerone2(new KingSize()),
      new Twix2(new FunSize()),
      new Snickers2(new FunSize()),
      new Mars2(new FunSize())
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
   * Returns a string representation of detached house.
   * @return a string representation of detached house.
   */
  @Override
  public String toString() {
    return TYPE;
  }

}
