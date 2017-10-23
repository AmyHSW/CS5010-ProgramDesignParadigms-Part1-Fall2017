package edu.neu.ccs.cs5010.assignment4;

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

  @Override
  public String toString() {
    return TYPE;
  }
}
