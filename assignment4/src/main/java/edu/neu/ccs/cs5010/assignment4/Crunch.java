package edu.neu.ccs.cs5010.assignment4;

public class Crunch extends Candy {

  private static final String TYPE = "Crunch";

  public Crunch(Size size) {
    super(size);
  }

  @Override
  public String getType() {
    return TYPE;
  }

}
