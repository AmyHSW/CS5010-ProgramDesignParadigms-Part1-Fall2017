package edu.neu.ccs.cs5010.assignment4;

import edu.neu.ccs.cs5010.assignment4.bonuspart1.DetachedHouse2;
import edu.neu.ccs.cs5010.assignment4.bonuspart1.Duplex2;
import edu.neu.ccs.cs5010.assignment4.bonuspart1.Mansion2;
import edu.neu.ccs.cs5010.assignment4.bonuspart1.Townhome2;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NeighborhoodTraversalTest {

  private INeighborhoodTraversal neighborhoodTraversal = null;
  private INeighborhoodTraversal neighborhoodTraversal2 = null;

  @Before
  public void setUp() throws Exception {
    String[] candies = {
        "super size mars",
        "KING SIZE TWIX",
        "FUn size whoopers",
        "snickers"
    };
    Household[] neighborhood = {
        new Mansion(), new DetachedHouse(), new Duplex(), new Townhome()
    };
    neighborhoodTraversal = new NeighborhoodTraversal(neighborhood, candies);

    Household[] neighborhood2 = {
        new Mansion2(), new DetachedHouse2(), new Duplex2(), new Townhome2()
    };
    neighborhoodTraversal2 = new NeighborhoodTraversal(neighborhood2, candies);
  }

  @Test
  public void hasTraversal() throws Exception {
    assertTrue(neighborhoodTraversal.hasTraversal());
    assertTrue(neighborhoodTraversal2.hasTraversal());
  }

  @Test
  public void getTraversal() throws Exception {
    String string = "Candy type, Candy size, Household type\n"
        + "Mars, Super Size, Mansion\n"
        + "Twix, King Size, Duplex\n"
        + "Whoopers, Fun Size, Duplex\n"
        + "Snickers, Regular Size, Townhome\n";

    String traversal = neighborhoodTraversal.getTraversal();
    String traversal2 = neighborhoodTraversal2.getTraversal();
    assertTrue(traversal.toLowerCase().equals(string.toLowerCase()));
    assertTrue(traversal2.toLowerCase().equals(string.toLowerCase()));
  }

}