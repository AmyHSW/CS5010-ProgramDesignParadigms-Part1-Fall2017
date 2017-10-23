package edu.neu.ccs.cs5010.assignment4.bonuspart1;

import edu.neu.ccs.cs5010.assignment4.InvalidInputException;
import org.junit.Test;

import java.io.IOException;

public class HalloweenNeighborhoodTraversal2Test {

  @Test
  public void testMain() throws Exception {
    String[] args1 = {
        "1", "DreamCandy1.csv"
    };
    HalloweenNeighborhoodTraversal2.main(args1);

    String[] args2 = {
        "1", "DreamCandy2.csv"
    };
    HalloweenNeighborhoodTraversal2.main(args2);

    String[] args3 = {
        "2", "DreamCandy1.csv", "DreamCandy2.csv"
    };
    HalloweenNeighborhoodTraversal2.main(args3);
  }

  @Test(expected = InvalidInputException.class)
  public void expectedInvalidInputException() throws Exception {
    String[] args = {
        "2", "DreamCandy1.csv"
    };
    HalloweenNeighborhoodTraversal2.main(args);
  }

  @Test(expected = IOException.class)
  public void expectedIOException() throws Exception {
    String[] args = {
        "1", "2"
    };
    HalloweenNeighborhoodTraversal2.main(args);
  }
}