package edu.neu.ccs.cs5010.assignment4;

public class Twix extends Candy {

  private static final String TYPE = "Twix";

  public Twix(Size size) {
    super(size);
  }

  @Override
  public String getType() {
    return TYPE;
  }

}
