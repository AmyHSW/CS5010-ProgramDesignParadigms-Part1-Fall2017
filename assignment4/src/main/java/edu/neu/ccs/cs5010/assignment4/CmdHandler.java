package edu.neu.ccs.cs5010.assignment4;

import java.util.Arrays;

/**
 * The <code>CmdHandler</code> class validates the format of command-line arguments and
 * parses the arguments.
 *
 * @author Shuwan Huang
 */
public class CmdHandler implements ICmdHandler {

  private String[] csvFiles = null;

  /**
   * Constructs a new CmdHandler with the command-line arguments.
   * @param args the command-line arguments
   */
  public CmdHandler(String[] args) {
    if (validate(args)) {
      parseArgs(args);
    }
  }

  private boolean validate(String[] args) {
    if (args == null || args.length == 0) {
      return false;
    }
    int num = parseInt(args[0]);
    return num == args.length - 1;
  }

  private int parseInt(String string) {
    try {
      return Integer.parseInt(string);
    } catch (NumberFormatException exception) {
      return -1;
    }
  }

  private void parseArgs(String[] args) {
    csvFiles = Arrays.copyOfRange(args, 1, args.length);
  }

  /**
   * Returns true if the arguments are valid.
   * @return true if the arguments are valid, false otherwise.
   */
  @Override
  public boolean isValid() {
    return csvFiles != null;
  }

  /**
   * Returns a string array of csv file names.
   * @return a string array of csv file names.
   */
  @Override
  public String[] getCSVFiles() {
    return Arrays.copyOf(csvFiles, csvFiles.length);
  }
}
