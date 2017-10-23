package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Townhome</code> class represents a townhome.
 *
 * @author Shuwan Huang
 */
public class Townhome implements Household {

  private static final String TYPE = "Townhome";
  private static final Candy[] CANDIES = (Candy[]) new Candy[] {
      new AlmondJoy(new RegularSize()),
      new BabyRuth(new RegularSize()),
      new KitKat(new RegularSize()),
      new Mars(new RegularSize()),
      new Snickers(new RegularSize()),
      new Toblerone(new RegularSize()),
      new Twix(new RegularSize()),
      new Whoopers(new RegularSize())
  };

  /**
   * Returns true if this household offers the candy specified by the candy name.
   * @param candyName a string representation of candy name
   * @return true if this household offers the candy specified by the candy name.
   */
  @Override
  public boolean hasCandy(String candyName) {
    return accept(new CandyVisitor(candyName));
  }

  private boolean accept(GenericVisitor<Boolean> visitor) {
    for (Candy candy : CANDIES) {
      if (candy.accept(visitor)) {
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
