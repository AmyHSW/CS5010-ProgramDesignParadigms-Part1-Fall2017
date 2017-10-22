package edu.neu.ccs.cs5010.assignment4.visitorPattern;

import edu.neu.ccs.cs5010.assignment4.visitorPattern.Candy;
import edu.neu.ccs.cs5010.assignment4.visitorPattern.Size;

public class Twix extends Candy {

  private static final String TYPE = "Twix";

  public Twix(Size size) {
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
