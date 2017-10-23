package edu.neu.ccs.cs5010.assignment4;

public class Snickers extends Candy {

  private static final String TYPE = "Snickers";

  public Snickers(Size size) {
    super(size);
  }

  @Override
  public String getType() {
    return TYPE;
  }

}
