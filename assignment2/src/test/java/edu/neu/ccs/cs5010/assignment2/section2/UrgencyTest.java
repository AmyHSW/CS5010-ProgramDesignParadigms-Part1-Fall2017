package edu.neu.ccs.cs5010.assignment2.section2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrgencyTest {

  private IUrgency urgency = null;

  @Before
  public void setUp() throws Exception {
    urgency = new Urgency(3);
  }

  @Test
  public void getLevel() throws Exception {
    assertTrue(urgency.getLevel() == 3);
  }

  @Test
  public void compareTo() throws Exception {
    assertTrue(urgency.compareTo(new Urgency(2)) > 0);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(urgency.equals(urgency));
    assertTrue(!urgency.equals("4"));
  }

  @Test
  public void testHashCode() throws Exception {
  }

  @Test(expected = InvalidUrgencyLevelException.class)
  public void expectedInvalidUrgencyLevelException() throws Exception {
    new Urgency(11);
  }
}