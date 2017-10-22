package edu.neu.ccs.cs5010.assignment4.visitorPattern;

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
