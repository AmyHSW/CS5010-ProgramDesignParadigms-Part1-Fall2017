package edu.neu.ccs.cs5010.assignment4;

import edu.neu.ccs.cs5010.assignment4.MainController;

import java.io.IOException;

/**
 * The <code>HalloweenNeighborhoodTraversal2</code> accepts the command-line arguments and passes
 * it to MainController to start the program.
 *
 * @author Shuwan Huang
 */
public class HalloweenNeighborhoodTraversal2 {

  private static final int INTERPRETER_PATTERN = 2;

  /**
   * Accepts the command-line arguments and passes it to MainController together with the
   * choice of design pattern.
   * @param args command-line arguments
   * @throws IOException if fails to open any csv files
   */
  public static void main(String[] args) throws IOException {
    new MainController(args, INTERPRETER_PATTERN);
  }

}
