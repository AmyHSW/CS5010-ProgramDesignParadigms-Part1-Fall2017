package edu.neu.ccs.cs5010.assignment4.visitorPattern;

public abstract class Candy implements ICandy {

  private final Size size;

  public Candy(Size size) {
    this.size = size;
  }

  @Override
  public boolean accept(GenericVisitor<Boolean> visitor) {
    return visitor.visit(this);
  }

  public Size getSize() {
    return size;
  }

  public String getType() {
    return null;
  }

}
