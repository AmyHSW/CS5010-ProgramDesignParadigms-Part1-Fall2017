package edu.neu.ccs.cs5010.assignment4;

import java.util.Arrays;

public class NeighborhoodTraversal implements INeighborhoodTraversal {

  private boolean exists = false;
  private String traversal = "Candy type, Candy size, Household type\n";

  private final Household[] neighborhood;

  public NeighborhoodTraversal(Household[] neighborhood, String[] candies) {
    this.neighborhood = Arrays.copyOf(neighborhood, neighborhood.length);
    findCandies(candies);
  }

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

  @Override
  public boolean hasTraversal() {
    return exists;
  }

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

  @Override
  public String getTraversal() {
    if (hasTraversal()) {
      return traversal;
    } else {
      return null;
    }
  }

}
