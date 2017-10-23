package edu.neu.ccs.cs5010.assignment4;

import java.util.Arrays;

public class CmdHandler implements ICmdHandler {

  private boolean valid = false;
  private String[] csvFiles = null;

  public CmdHandler(String[] args) {
    validate(args);
  }

  private void validate(String[] args) {
    if (args == null || args.length == 0) {
      return;
    }

    int num = parseInt(args[0]);
    if (num == args.length - 1) {
      valid = true;
    }

    csvFiles = Arrays.copyOfRange(args, 1, args.length);
  }

  private int parseInt(String string) {
    try {
      return Integer.parseInt(string);
    } catch (NumberFormatException exception) {
      return -1;
    }
  }

  @Override
  public boolean isValid() {
    return valid;
  }

  @Override
  public String[] getCSVFiles() {
    return Arrays.copyOf(csvFiles, csvFiles.length);
  }
}
