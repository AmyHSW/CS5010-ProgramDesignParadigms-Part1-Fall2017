package edu.neu.ccs.cs5010.assignment4.visitorPattern;

public class AlmondJoy extends Candy {

  private static final String TYPE = "Almond Joy";

  public AlmondJoy(Size size) {
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
