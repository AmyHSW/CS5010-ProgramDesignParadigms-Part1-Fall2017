package edu.neu.ccs.cs5010.assignment4.visitorPattern;

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
