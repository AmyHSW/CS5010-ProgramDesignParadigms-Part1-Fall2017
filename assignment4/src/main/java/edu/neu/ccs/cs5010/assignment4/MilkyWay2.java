package edu.neu.ccs.cs5010.assignment4;

public class MilkyWay2 extends Candy2 {

  private static final String TYPE = "Milky Way";

  public MilkyWay2(Size size) {
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
