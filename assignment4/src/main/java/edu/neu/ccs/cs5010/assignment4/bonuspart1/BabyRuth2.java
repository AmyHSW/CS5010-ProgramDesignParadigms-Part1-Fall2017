package edu.neu.ccs.cs5010.assignment4.bonuspart1;

import edu.neu.ccs.cs5010.assignment4.Size;

public class BabyRuth2 extends Candy2 {

  private static final String TYPE = "Baby Ruth";

  public BabyRuth2(Size size) {
    super(size);
  }

  @Override
  public boolean typeAndSizeCheck(String candyName) {
    String thisCandyName = (getSize() + " " + TYPE).toLowerCase();

    String thatCandyName = candyName.toLowerCase();
    if (!thatCandyName.contains("size")) {
      thatCandyName = "regular size " + thatCandyName;
    }

    return thisCandyName.equals(thatCandyName);
  }

}
