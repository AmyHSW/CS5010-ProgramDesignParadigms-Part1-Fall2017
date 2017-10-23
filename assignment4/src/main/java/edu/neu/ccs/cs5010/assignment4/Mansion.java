package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Mansion</code> class represents a mansion household.
 *
 * @author Shuwan Huang
 */
public class Mansion implements Household {

  private static final String TYPE = "Mansion";
  private static final Candy[] CANDIES = (Candy[]) new Candy[] {
      new Twix(new SuperSize()),
      new Snickers(new SuperSize()),
      new Mars(new SuperSize()),
      new KitKat(new KingSize()),
      new Whoopers(new KingSize()),
      new Crunch(new KingSize()),
      new Toblerone(new FunSize()),
      new BabyRuth(new FunSize()),
      new AlmondJoy(new FunSize())
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
   * Returns a string representation of mansion.
   * @return a string representation of mansion.
   */
  @Override
  public String toString() {
    return TYPE;
  }
}
