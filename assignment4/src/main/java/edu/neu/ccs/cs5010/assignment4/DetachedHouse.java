package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>DetachedHouse</code> class represents a detached house.
 *
 * @author Shuwan Huang
 */
public class DetachedHouse implements Household {

  private static final String TYPE = "Detached House";
  private static final Candy[] CANDIES = (Candy[]) new Candy[] {
      new KitKat(new SuperSize()),
      new Whoopers(new SuperSize()),
      new MilkyWay(new SuperSize()),
      new Crunch(new SuperSize()),
      new Toblerone(new KingSize()),
      new Twix(new FunSize()),
      new Snickers(new FunSize()),
      new Mars(new FunSize())
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
   * Returns a string representation of detached house.
   * @return a string representation of detached house.
   */
  @Override
  public String toString() {
    return TYPE;
  }

}
