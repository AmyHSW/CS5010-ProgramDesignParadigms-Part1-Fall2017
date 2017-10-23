package edu.neu.ccs.cs5010.assignment4.bonuspart1;

import edu.neu.ccs.cs5010.assignment4.FunSize;
import edu.neu.ccs.cs5010.assignment4.Household;
import edu.neu.ccs.cs5010.assignment4.KingSize;
import edu.neu.ccs.cs5010.assignment4.SuperSize;

public class DetachedHouse2 implements Household {

  private static final String TYPE = "Detached House";
  private static final Candy2[] CANDIES = (Candy2[]) new Candy2[] {
      new KitKat2(new SuperSize()),
      new Whoopers2(new SuperSize()),
      new MilkyWay2(new SuperSize()),
      new Crunch2(new SuperSize()),
      new Toblerone2(new KingSize()),
      new Twix2(new FunSize()),
      new Snickers2(new FunSize()),
      new Mars2(new FunSize())
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
