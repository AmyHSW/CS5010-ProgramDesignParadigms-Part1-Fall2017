package edu.neu.ccs.cs5010.assignment4;

public class Duplex2 implements Household {

  private static final String TYPE = "Duplex";
  private static final Candy2[] CANDIES = (Candy2[]) new Candy2[] {
      new Toblerone2(new SuperSize()),
      new BabyRuth2(new SuperSize()),
      new AlmondJoy2(new SuperSize()),
      new Twix2(new KingSize()),
      new Snickers2(new KingSize()),
      new Mars2(new KingSize()),
      new KitKat2(new FunSize()),
      new Whoopers2(new FunSize()),
      new MilkyWay2(new FunSize()),
      new Crunch2(new FunSize())
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
