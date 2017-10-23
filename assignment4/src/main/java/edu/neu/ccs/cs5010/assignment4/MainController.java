package edu.neu.ccs.cs5010.assignment4;

import edu.neu.ccs.cs5010.assignment4.bonuspart1.DetachedHouse2;
import edu.neu.ccs.cs5010.assignment4.bonuspart1.Duplex2;
import edu.neu.ccs.cs5010.assignment4.bonuspart1.Mansion2;
import edu.neu.ccs.cs5010.assignment4.bonuspart1.Townhome2;

import java.io.File;
import java.io.IOException;

public class MainController {

  private static final int VISITOR_PATTERN = 1;
  private static final int INTERPRETER_PATTERN = 2;

  private static final Household[] NEIGHBORHOOD_VISITOR = {
      new Mansion(), new Duplex(), new DetachedHouse(), new Townhome()
  };

  private static final Household[] NEIGHBORHOOD_INTERPRETER = {
      new Mansion2(), new Duplex2(), new DetachedHouse2(), new Townhome2()
  };

  public MainController(String[] args, int pattern) throws IOException {
    ICmdHandler handler = new CmdHandler(args);
    validate(handler);
    validate(pattern);
    parseAndEvaluateDreamCandies(handler.getCSVFiles(), pattern);
  }

  private void validate(ICmdHandler handler) {
    if (!handler.isValid()) {
      throw new InvalidInputException("Command-line arguments are in wrong format!");
    }
  }

  private void validate(int pattern) {
    if (pattern != VISITOR_PATTERN && pattern != INTERPRETER_PATTERN) {
      throw new InvalidDesignPatternException("Design patter " + pattern + " is not supported.");
    }
  }

  private void parseAndEvaluateDreamCandies(String[] csvFiles, int pattern) throws IOException {
    for (String csvFile : csvFiles) {
      String[] dreamCandies = ((new CsvParser(csvFile)).getCandyNames());
      String traversal = evaluate(dreamCandies, pattern);
      if (traversal != null) {
        String output = "DreamTraversal" + csvFile.substring(csvFile.indexOf(".csv") - 1);
        File file = new File(output);
        IOLibrary.writeStringToFile(file, traversal);
        System.out.println(csvFile + ": Traversal has been saved to " + output);
      } else {
        System.out.println(csvFile
            + ": No traversal because the list contains an unsupported candy");
      }
    }
  }

  private String evaluate(String[] dreamCandies, int pattern) {
    INeighborhoodTraversal neighborhoodTraversal;
    if (pattern == VISITOR_PATTERN) {
      neighborhoodTraversal = new NeighborhoodTraversal(NEIGHBORHOOD_VISITOR, dreamCandies);
    } else {
      neighborhoodTraversal = new NeighborhoodTraversal(NEIGHBORHOOD_INTERPRETER, dreamCandies);
    }
    return neighborhoodTraversal.getTraversal();
  }

}
