package edu.neu.ccs.cs5010.assignment4.visitorPattern;

public class BabyRuth extends Candy {

  private static final String TYPE = "Baby Ruth";

  public BabyRuth(Size size) {
    super(size);
  }

  @Override
  public String getType() {
    return TYPE;
  }

  @Override
  public String toString() {
    return getType() + ", " + getSize();
  }
}
