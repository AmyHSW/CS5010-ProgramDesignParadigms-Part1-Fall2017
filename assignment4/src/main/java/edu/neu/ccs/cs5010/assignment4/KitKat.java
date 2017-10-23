package edu.neu.ccs.cs5010.assignment4;

public class KitKat extends Candy {

  private static final String TYPE = "Kit Kat";

  public KitKat(Size size) {
    super(size);
  }

  @Override
  public String getType() {
    return TYPE;
  }

}
