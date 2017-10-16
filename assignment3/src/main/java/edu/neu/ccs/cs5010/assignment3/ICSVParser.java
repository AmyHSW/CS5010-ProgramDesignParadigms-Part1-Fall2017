package edu.neu.ccs.cs5010.assignment3;

import java.util.List;

/**
 * The <code>ICSVParser</code> interface represents a csv parser.
 *
 * @author Shuwan Huang
 */
public interface ICSVParser {

  /**
   * Returns a list of lists of string, the strings are tokens in the original csv file.
   * @return a list of lists of string
   */
  List<List<String>> getTokens();
}
