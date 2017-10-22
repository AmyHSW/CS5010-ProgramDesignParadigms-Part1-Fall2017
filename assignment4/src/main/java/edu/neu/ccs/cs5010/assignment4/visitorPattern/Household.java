package edu.neu.ccs.cs5010.assignment4.visitorPattern;

public interface Household {
  Candy accept(GenericVisitor<Boolean> visitor);
}
