package edu.neu.ccs.cs5010.assignment4.bonuspart1;

import edu.neu.ccs.cs5010.assignment4.Household;
import edu.neu.ccs.cs5010.assignment4.RegularSize;

public class Townhome2 implements Household {

  private static final String TYPE = "Townhome";
  private static final Candy2[] CANDIES = (Candy2[]) new Candy2[] {
      new AlmondJoy2(new RegularSize()),
      new BabyRuth2(new RegularSize()),
      new KitKat2(new RegularSize()),
      new Mars2(new RegularSize()),
      new Snickers2(new RegularSize()),
      new Toblerone2(new RegularSize()),
      new Twix2(new RegularSize()),
      new Whoopers2(new RegularSize())
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
