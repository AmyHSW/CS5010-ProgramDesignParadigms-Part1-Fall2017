package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>Duplex</code> class represents a duplex household.
 *
 * @author Shuwan Huang
 */
public class Duplex implements Household {

  private static final String TYPE = "Duplex";
  private static final Candy[] CANDIES = (Candy[]) new Candy[] {
    new Toblerone(new SuperSize()),
    new BabyRuth(new SuperSize()),
    new AlmondJoy(new SuperSize()),
    new Twix(new KingSize()),
    new Snickers(new KingSize()),
    new Mars(new KingSize()),
    new KitKat(new FunSize()),
    new Whoopers(new FunSize()),
    new MilkyWay(new FunSize()),
    new Crunch(new FunSize())
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
   * Returns a string representation of Duplex.
   * @return a string representation of Duplex.
   */
  @Override
  public String toString() {
    return TYPE;
  }

}
