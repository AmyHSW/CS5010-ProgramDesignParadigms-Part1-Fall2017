package edu.neu.ccs.cs5010.assignment4;

public class Whoopers extends Candy {

  private static final String TYPE = "Whoopers";

  public Whoopers(Size size) {
    super(size);
  }

  @Override
  public String getType() {
    return TYPE;
  }

}
