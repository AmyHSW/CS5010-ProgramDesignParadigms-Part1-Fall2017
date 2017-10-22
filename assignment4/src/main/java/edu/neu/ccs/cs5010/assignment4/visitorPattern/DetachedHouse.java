package edu.neu.ccs.cs5010.assignment4.visitorPattern;

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

  @Override
  public Candy accept(GenericVisitor<Boolean> visitor) {
    for (Candy candy : CANDIES) {
      if (candy.accept(visitor)) {
        return candy;
      }
    }
    return null;
  }


  @Override
  public String toString() {
    return TYPE;
  }
}
