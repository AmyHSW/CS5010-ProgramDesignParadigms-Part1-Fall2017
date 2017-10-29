package edu.neu.ccs.cs5010.assignment5;

public class CmdHandler implements ICmdHandler {

  private static final int LENGTH_ARGS = 4;
  private static final int NUM_CLIENTS_UPPER_BOUND = 50000;
  private static final int NUM_VERIFICATIONS_UPPER_BOUND = 10000;

  private final boolean valid;
  private final StringBuilder errorMsg = new StringBuilder();
  private int numClients;
  private int numVerifications;
  private double fraction;
  private String outputDir;

  public CmdHandler(String[] args) {
    valid = validate(args);
  }

  private boolean validate(String[] args) {
    if (args.length != LENGTH_ARGS) {
      errorMsg.append("Please give four input arguments.\n");
      return false;
    }
    return validateNumClients(args[0])
        && validateNumVerifications(args[1])
        && validateFraction(args[2])
        && validateOutputDir(args[3]);
  }

  private boolean validateNumClients(String arg) {
    try {
      numClients = Integer.parseInt(arg);
      if (numClients >= 0 && numClients <= NUM_CLIENTS_UPPER_BOUND) {
        return true;
      } else {
        errorMsg.append("Number of unique bank clients is not between 0 and ")
            .append(NUM_CLIENTS_UPPER_BOUND)
            .append(": ")
            .append(numClients)
            .append("\n");
        return false;
      }
    } catch (NumberFormatException exeption) {
      errorMsg.append("Please provide an integer for the number of unique bank clients: ")
          .append(arg);
      return false;
    }
  }

  private boolean validateNumVerifications(String arg) {
    try {
      numVerifications = Integer.parseInt(arg);
      if (numVerifications >= 0 && numVerifications <= NUM_VERIFICATIONS_UPPER_BOUND) {
        return true;
      } else {
        errorMsg.append("Number of unique verifications is not between 0 and ")
            .append(NUM_VERIFICATIONS_UPPER_BOUND)
            .append(": ")
            .append(numVerifications)
            .append("\n");
        return false;
      }
    } catch (NumberFormatException exception) {
      errorMsg.append("Please provide an integer for the number of verifications: ")
          .append(arg);
      return false;
    }
  }

  private boolean validateFraction(String arg) {
    try {
      fraction = Double.parseDouble(arg);
      if (fraction >= 0 && fraction <= 1) {
        return true;
      } else {
        errorMsg.append("Fraction of invalid messages is not between 0 and 1: ")
            .append(fraction)
            .append("\n");
        return false;
      }
    } catch (NumberFormatException exception) {
      errorMsg.append("Please provide a double for fraction of invalid messages: ")
          .append(arg);
      return false;
    }
  }

  private boolean validateOutputDir(String arg) {
    if (arg == null || !arg.endsWith(".csv")) {
      errorMsg.append("Please provide an output csv file: ")
          .append(arg);
      return false;
    }
    outputDir = arg;
    return true;
  }

  @Override
  public boolean isValid() {
    return valid;
  }

  @Override
  public String getErrorMessage() {
    return errorMsg.toString();
  }

  @Override
  public int getNumClients() {
    return numClients;
  }

  @Override
  public int getNumVerifications() {
    return numVerifications;
  }

  @Override
  public double getFraction() {
    return fraction;
  }

  @Override
  public String getOutputDir() {
    return outputDir;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    CmdHandler that = (CmdHandler) other;

    if (valid != that.valid) {
      return false;
    }
    if (numClients != that.numClients) {
      return false;
    }
    if (numVerifications != that.numVerifications) {
      return false;
    }
    if (Double.compare(that.fraction, fraction) != 0) {
      return false;
    }
    return outputDir != null ? outputDir.equals(that.outputDir) : that.outputDir == null;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = (valid ? 1 : 0);
    result = 31 * result + numClients;
    result = 31 * result + numVerifications;
    temp = Double.doubleToLongBits(fraction);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (outputDir != null ? outputDir.hashCode() : 0);
    return result;
  }
}
