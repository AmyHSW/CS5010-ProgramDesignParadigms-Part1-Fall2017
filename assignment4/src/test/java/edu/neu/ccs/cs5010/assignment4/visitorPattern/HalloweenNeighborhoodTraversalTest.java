package edu.neu.ccs.cs5010.assignment4.visitorPattern;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HalloweenNeighborhoodTraversalTest {

  private IHalloweenNeighborhoodTraversal traversal = null;

  @Before
  public void setUp() throws Exception {
    String[] candies = {
        "super size mars",
        "KING SIZE TWIX",
        "FUn size whoopers",
        "snickers"
    };
    traversal = new HalloweenNeighborhoodTraversal(candies);
  }

  @Test
  public void hasTraversal() throws Exception {
    assertTrue(traversal.hasTraversal());
  }

  @Test
  public void getTraversal() throws Exception {
    String string = "Candy type, Candy size, Household type\n"
        + "Mars, Super Size, Mansion\n"
        + "Twix, King Size, Duplex\n"
        + "Whoopers, Fun Size, Duplex\n"
        + "Snickers, Regular Size, Townhome\n";
    assertTrue(traversal.getTraversal().equals(string));
  }

  @Test
  public void main() throws Exception {
    String[] args1 = {
        "1", "DreamCandy1.csv"
    };
    HalloweenNeighborhoodTraversal.main(args1);

    String[] args2 = {
        "1", "DreamCandy2.csv"
    };
    HalloweenNeighborhoodTraversal.main(args2);

  }


  @Test(expected = InvalidInputException.class)
  public void expectedInvalidInputException() throws Exception {
    String[] args = {
        "2", "DreamCandy1.csv"
    };
    HalloweenNeighborhoodTraversal.main(args);
  }

  @Test(expected = IOException.class)
  public void expectedIOException() throws Exception {
    String[] args = {
        "1", "2"
    };
    HalloweenNeighborhoodTraversal.main(args);
  }
}