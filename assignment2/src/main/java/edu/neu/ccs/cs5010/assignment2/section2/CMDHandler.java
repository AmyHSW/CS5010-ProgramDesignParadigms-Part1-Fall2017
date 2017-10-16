package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * The <code>CMDHandler</code> class validates the format of arguments and parses the arguments
 * into information of simulation time, simulation mode, number of rooms, max pause between
 * patients generation, and max treatment duration.
 * <p></p>
 * It provides a method <code>isLegalFormat</code> to check if arguments are in legal format,
 * and getters to obtain the above information.
 *
 * @author Shuwan Huang
 */
public class CMDHandler implements ICMDHandler {

  private static final String SIMULATION_TIME = "--simulation-time";
  private static final String NUM_ROOMS = "--number-rooms";
  private static final String MODE = "--mode";
  private static final String MAX_PAUSE = "--max-pause";
  private static final String MAX_TREATMENT = "--max-treatment";
  private static final String USAGE_MSG =
      "Usage: \n\n"
          + "--simulation-time <min>  accepts an integer that represents the simulation time\n"
          + "                         in minutes\n\n"
          + "--number-rooms <int>     accepts an integer that represents the number of rooms\n\n"
          + "--mode <int>             accepts an integer that represents the mode of simulation,\n"
          + "                         1 for preset, 2 for random\n\n"
          + "--max-pause <min>        accepts an integer that represents the max pause between \n"
          + "                         patient generation, in minutes (if it is in preset mode,\n"
          + "                         this is the exact pause time)\n\n"
          + "--max-treatment <min>    accepts an integer that represents the max treatment\n"
          + "                         duration in minutes (if it is in preset mode, this is\n"
          + "                         the exact treatment duration)\n\n"
          + "For example:\n\n"
          + "--simulation-time  30  --number-rooms  5  --mode 1\n"
          + "--max-pause 60 --max-treatment 5\n";

  private final Map<String, Integer> userInput; // stores the user-input arguments
  private final StringBuilder errorMsg; // stores error message

  /**
   * Constructs a new CMDHandler with the given String array. Initializes the userInput as
   * a HashMap that stores user input information with attributes as keys. Initializes
   * the errorMsg as an empty StringBuilder. Parses the arguments and stores information
   * in userInput map.
   *
   * @param args a String array
   */
  public CMDHandler(String[] args) {
    userInput = new HashMap<>();
    userInput.put(SIMULATION_TIME, null);
    userInput.put(NUM_ROOMS, null);
    userInput.put(MODE, null);
    userInput.put(MAX_PAUSE, null);
    userInput.put(MAX_TREATMENT, null);

    errorMsg = new StringBuilder();

    parseArgs(args);
  }

  // parses the arguments and puts them in map if arguments are in legal format.
  private void parseArgs(String[] args) {
    int i = 0;

    while (i < args.length) {

      String arg = args[i++];

      // if this arg is not a header, continue
      if (!isHeader(arg)) continue;

      // if this arg is a header but can not be recognized,
      // appends error message and continue
      if (!isRecognizable(arg)) {
        errorMsg.append(arg);
        errorMsg.append(" cannot be recognized\n");
        continue;
      }

      // if this argument is the last argument,
      // or if next argument is also a header, appends error message and continue
      if (i >= args.length || isHeader(args[i])) {
        errorMsg.append(arg);
        errorMsg.append(" was given without providing appropriate argument\n");
        continue;
      }

      // validates that the argument is an integer
      int integer = parse(args[i++]);
      if (integer != -1) userInput.put(arg, integer);
    }
  }

  // returns true if the argument starts with "--"
  private boolean isHeader(String arg) {
    return arg.startsWith("--");
  }

  // returns true if the argument is one of the keys in user input map
  private boolean isRecognizable(String arg) {
    return userInput.containsKey(arg);
  }

  // if the arg is an integer, returns the integer; if not, returns -1.
  private int parse(String arg) {
    try {
      int integer = Integer.parseInt(arg);
      return integer;
    } catch (NumberFormatException exception) {
      errorMsg.append(arg);
      errorMsg.append(" should be an integer\n");
      return -1;
    }
  }

  /**
   * Returns true if the required information are provided in legal format.
   *
   * @return true if arguments are in legal format and false otherwise.
   */
  @Override
  public boolean isLegalFormat() {
    return userInput.get(SIMULATION_TIME) != null
        && userInput.get(MODE) != null
        && userInput.get(MAX_TREATMENT) != null
        && userInput.get(MAX_PAUSE) != null;
  }

  /**
   * Returns the error message if arguments are in illegal format; null if arguments are in
   * legal format.
   *
   * @return the error message if arguments are in illegal format; null if arguments are in
   * legal format.
   */
  @Override
  public String getErrorMessage() {
    if (isLegalFormat()) return null;
    if (errorMsg.toString().equals("")) {
      errorMsg.append("Some required arguments are missing! Please see the example below.\n");
    }
    return "Error:\n\n" + errorMsg.toString() + "\n" + USAGE_MSG;
  }

  /**
   * Returns the mode of simulation; -1 if arguments are not in legal format.
   * @return the mode of simulation; -1 if arguments are not in legal format.
   */
  @Override
  public int getMode() {
    if (!isLegalFormat()) return -1;
    return userInput.get(MODE);
  }

  /**
   * Returns the number of rooms; -1 if arguments are not in legal format.
   * @return the number of rooms; -1 if arguments are not in legal format.
   */
  @Override
  public int getNumRooms() {
    if (!isLegalFormat()) return -1;
    return userInput.get(NUM_ROOMS);
  }

  /**
   * Returns the duration of simulation; null if arguments are not in legal format.
   * @return the duration of simulation; null if arguments are not in legal format.
   */
  @Override
  public Duration getSimulationTime() {
    if (!isLegalFormat()) return null;
    return Duration.ofMinutes(userInput.get(SIMULATION_TIME));
  }

  /**
   * Returns the max duration of treatment; null if arguments are not in legal format
   * @return the max duration of treatment; null if arguments are not in legal format
   */
  @Override
  public Duration getMaxTreatment() {
    if (!isLegalFormat()) return null;
    return Duration.ofMinutes(userInput.get(MAX_TREATMENT));
  }

  /**
   * Returns the max pause between patients generation; null if arguments are not in legal format
   * @return the max pause between patients generation; null if arguments are not in legal format
   */
  @Override
  public Duration getMaxPause() {
    if (!isLegalFormat()) return null;
    return Duration.ofMinutes(userInput.get(MAX_PAUSE));
  }

}
