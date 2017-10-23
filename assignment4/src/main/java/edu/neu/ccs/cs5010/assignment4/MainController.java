package edu.neu.ccs.cs5010.assignment4;

import java.io.File;
import java.io.IOException;

/**
 * The <code>MainController</code> class is the main controller of halloween neighborhood
 * traversal program. This class validates the command-line arguments, parses the csv files
 * to dream candies lists. For each dream candy list, it checks if there exists a
 * traversal to achieve the all candies in the list. If yes, save the traversal to an
 * output file; if not prints the message to console, and continue on next dream candy list.
 *
 * <p>Depending on the design pattern that user decides to use, the main controller will choose
 * the corresponding implementation of neighborhood to find the traversal.
 *
 * @author Shuwan Huang
 */
public class MainController {

  private static final int VISITOR_PATTERN = 1;
  private static final int INTERPRETER_PATTERN = 2;

  // the households that are implemented using visitor pattern
  private static final Household[] NEIGHBORHOOD_VISITOR = {
      new Mansion(), new Duplex(), new DetachedHouse(), new Townhome()
  };

  // the households that are implements using interpreter pattern
  private static final Household[] NEIGHBORHOOD_INTERPRETER = {
      new Mansion2(), new Duplex2(), new DetachedHouse2(), new Townhome2()
  };

  private ICmdHandler cmdHandler;

  /**
   * Constructs a new MainController with command-line arguments and the selected
   * design pattern.
   * @param args command-line arguments
   * @param pattern design pattern
   * @throws IOException if there is an I/O failure
   */
  public MainController(String[] args, int pattern) throws IOException {
    validate(args);
    validate(pattern);

    evaluateDreamCandies(cmdHandler.getCSVFiles(), pattern);
  }

  // validates the format of command-line arguments
  private void validate(String[] args) {
    cmdHandler = new CmdHandler(args);
    if (!cmdHandler.isValid()) {
      throw new InvalidInputException("Command-line arguments are in wrong format!");
    }
  }

  // validates the pattern
  private void validate(int pattern) {
    if (pattern != VISITOR_PATTERN && pattern != INTERPRETER_PATTERN) {
      throw new InvalidDesignPatternException("Design patter " + pattern + " is not supported.");
    }
  }

  // for each csv file, parses the file to get dream candy list.
  // if there exists a traversal, saves it to an output file; if not, prints a message to console.
  // continues to next csv file.
  private void evaluateDreamCandies(String[] csvFiles, int pattern) throws IOException {
    for (String csvFile : csvFiles) {

      String[] dreamCandies = (new CsvParser(csvFile)).getCandyNames();
      String traversal = getTraversal(dreamCandies, pattern);

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

  // returns a traversal for the dream candies with the specified design pattern
  private String getTraversal(String[] dreamCandies, int pattern) {
    INeighborhoodTraversal neighborhoodTraversal;

    if (pattern == VISITOR_PATTERN) {
      neighborhoodTraversal = new NeighborhoodTraversal(NEIGHBORHOOD_VISITOR, dreamCandies);
    } else {
      neighborhoodTraversal = new NeighborhoodTraversal(NEIGHBORHOOD_INTERPRETER, dreamCandies);
    }

    return neighborhoodTraversal.getTraversal();
  }

}
