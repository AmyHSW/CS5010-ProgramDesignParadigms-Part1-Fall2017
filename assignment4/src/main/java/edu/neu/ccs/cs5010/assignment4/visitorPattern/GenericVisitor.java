package edu.neu.ccs.cs5010.assignment4.visitorPattern;

public interface GenericVisitor<T> {

  T visit(Candy candy);

}
