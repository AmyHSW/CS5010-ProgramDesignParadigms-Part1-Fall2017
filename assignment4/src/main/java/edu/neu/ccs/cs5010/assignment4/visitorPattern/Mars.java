package edu.neu.ccs.cs5010.assignment4.visitorPattern;

public class Mars extends Candy {

  private static final String TYPE = "Mars";

  public Mars(Size size) {
    super(size);
  }

  @Override
  public boolean accept(GenericVisitor<Boolean> visitor) {
    return visitor.visit(this);
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
