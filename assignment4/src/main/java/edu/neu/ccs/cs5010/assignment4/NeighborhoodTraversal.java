package edu.neu.ccs.cs5010.assignment4;

import java.util.Arrays;

/**
 * The <code>NeighborhoodTraversal</code> class checks whether or not there exists a neighborhood
 * traversal that achieves the desired candy list. The neighborhood is represented by an array of
 * households. The candy list is represented by an array of strings of candy names.
 *
 * <p>For each candy in the candy list, the <code>NeighborhoodTraversal</code> traverses all
 * households in the neighborhood until it finds the house that offers the desired candy.
 * If a candy cannot be found in the any households, then there does not exist a traversal to
 * achieve the entire candy list.
 *
 * @author Shuwan Huang
 */
public class NeighborhoodTraversal implements INeighborhoodTraversal {

  private boolean exists = false;
  private String traversal = "Candy type, Candy size, Household type\n";

  private final Household[] neighborhood;

  /**
   * Constructs a new NeighborhoodTraversal with an array of households and an array of
   * candy names.
   * @param neighborhood a household array
   * @param candies a string array
   */
  public NeighborhoodTraversal(Household[] neighborhood, String[] candies) {
    this.neighborhood = Arrays.copyOf(neighborhood, neighborhood.length);
    findCandies(candies);
  }

  // for each candy in the list, trys to find the candy in the neighborhood. If the candy
  // is found, record the household where it was found. If all candies are found, set the
  // exists variable to be true.
  private void findCandies(String[] candies) {
    for (String candyName : candies) {
      boolean findCandy = false;
      for (Household household : neighborhood) {
        if (household.hasCandy(candyName)) {
          findCandy = true;
          recordResult(candyName, household);
          break;
        }
      }
      if (!findCandy) {
        return;
      }
    }
    exists = true;
  }

  /**
   * Returns true if there exists an achievable neighborhood traversal.
   * @return true if there exists an achievable neighborhood traversal, false otherwise
   */
  @Override
  public boolean hasTraversal() {
    return exists;
  }

  // records the candy and the household where the candy was found.
  private void recordResult(String candyName, Household household) {
    String type;
    String size;
    int index = candyName.toLowerCase().indexOf("size");
    if (index == -1) {
      size = "Regular Size";
      type = candyName;
    } else {
      size = candyName.substring(0, index + "size".length());
      type = candyName.substring(index + "size".length() + 1);
    }
    traversal += type + ", " + size + ", " + household + '\n';
  }

  /**
   * Returns the traversal if there exists one, otherwise returns null.
   * @return the traversal if there exists one, otherwise returns null.
   */
  @Override
  public String getTraversal() {
    if (hasTraversal()) {
      return traversal;
    } else {
      return null;
    }
  }

}
