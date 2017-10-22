package edu.neu.ccs.cs5010.assignment4.visitorPattern;

import java.io.File;
import java.io.IOException;

public class HalloweenNeighborhoodTraversal implements IHalloweenNeighborhoodTraversal {

  private boolean exists = false;
  private String traversal = "Candy type, Candy size, Household type\n";

  private final Household[] neighborhood = (Household[]) new Household[]{
      new Mansion(), new DetachedHouse(), new Duplex(), new Townhome()
  };

  public HalloweenNeighborhoodTraversal(String[] candies) {
    findCandies(candies);
  }

  private void findCandies(String[] candies) {
    for (String candyName : candies) {
      boolean hasCandy = false;
      GenericVisitor<Boolean> visitor = new CandyVisitor(candyName);
      for (Household household : neighborhood) {
        Candy candy = household.accept(visitor);
        if (candy != null) {
          hasCandy = true;
          recordResult(candy, household);
          break;
        }
      }
      if (!hasCandy) {
        return;
      }
    }
    exists = true;
  }

  private void recordResult(Candy candy, Household household) {
    traversal += candy + ", " + household + '\n';
  }

  @Override
  public boolean hasTraversal() {
    return exists;
  }

  @Override
  public String getTraversal() {
    if (hasTraversal()) {
      return traversal;
    } else {
      return null;
    }
  }

  public static void main(String[] args) throws IOException {
    ICmdHandler handler = new CmdHandler(args);
    if (!handler.isValid()) {
      throw new InvalidInputException("Command-line arguments are in wrong format!");
    }
    String[] csvFiles = handler.getCSVFiles();
    for (String csvFile : csvFiles) {
      String[] candyNames = ((new CsvParser(csvFile)).getCandyNames());
      HalloweenNeighborhoodTraversal traversal = new HalloweenNeighborhoodTraversal(candyNames);
      if (traversal.hasTraversal()) {
        String output = "DreamTraversal" + csvFile.substring(csvFile.indexOf(".csv") - 1);
        File file = new File(output);
        IOLibrary.writeStringToFile(file, traversal.getTraversal());
        System.out.println(csvFile + ": Traversal has been saved to " + output);
      } else {
        System.out.println(csvFile +
            ": No traversal because the list contains an unsupported candy");
      }
    }
  }

}
