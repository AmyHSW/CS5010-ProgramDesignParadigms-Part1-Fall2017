package edu.neu.ccs.cs5010.assignment4.visitorPattern;

public interface ICandy {

  boolean accept(GenericVisitor<Boolean> visitor);
}
