package edu.neu.ccs.cs5010.assignment4;

public class AlmondJoy extends Candy {

  private static final String TYPE = "Almond Joy";

  public AlmondJoy(Size size) {
    super(size);
  }

  @Override
  public String getType() {
    return TYPE;
  }

}
