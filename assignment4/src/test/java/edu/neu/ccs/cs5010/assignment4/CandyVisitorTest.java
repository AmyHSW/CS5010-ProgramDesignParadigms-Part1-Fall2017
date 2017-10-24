package edu.neu.ccs.cs5010.assignment4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CandyVisitorTest {

  private GenericVisitor<Boolean> visitor;

  @Before
  public void setUp() throws Exception {
    visitor = new CandyVisitor("super size twix");
  }

  @Test
  public void visit() throws Exception {
    assertTrue(visitor.visit(new Twix(new SuperSize())));
  }

  @Test
  public void equals() throws Exception {
    assertTrue(visitor.equals(new CandyVisitor("super size twix")));
    assertTrue(!visitor.equals(null));
    assertTrue(!visitor.equals(new Twix(new SuperSize())));
    assertTrue(!visitor.equals(new CandyVisitor("twix")));
  }

  @Test
  public void testHashCode() throws Exception {
    GenericVisitor<Boolean> other = new CandyVisitor("super size twix");
    assertTrue(visitor.hashCode() == other.hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException() throws Exception {
    new CandyVisitor(null);
  }

}