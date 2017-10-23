package edu.neu.ccs.cs5010.assignment4;

import org.junit.Test;

import java.io.IOException;

public class HalloweenNeighborhoodTraversalTest {

  @Test
  public void testMain() throws Exception {
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