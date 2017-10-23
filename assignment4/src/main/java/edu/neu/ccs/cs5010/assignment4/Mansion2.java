package edu.neu.ccs.cs5010.assignment4;

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

  @Override
  public boolean hasCandy(String candyName) {
    for (Candy2 candy : CANDIES) {
      if (candy.typeAndSizeCheck(candyName)) {
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
