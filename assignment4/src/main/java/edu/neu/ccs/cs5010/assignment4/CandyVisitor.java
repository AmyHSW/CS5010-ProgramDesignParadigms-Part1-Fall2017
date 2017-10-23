package edu.neu.ccs.cs5010.assignment4;

public class CandyVisitor implements GenericVisitor<Boolean> {

  private final String candyName;

  public CandyVisitor(String candyName) {
    candyName = candyName.toLowerCase();
    if (!candyName.contains("size")) {
      this.candyName = "regular size " + candyName;
    } else {
      this.candyName = candyName;
    }
  }

  @Override
  public Boolean visit(Candy candy) {
    return candyName.equals((candy.getSize() + " " + candy.getType()).toLowerCase());
  }

}
