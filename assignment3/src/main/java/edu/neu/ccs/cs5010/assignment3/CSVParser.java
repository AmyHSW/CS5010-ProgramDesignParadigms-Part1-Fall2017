package edu.neu.ccs.cs5010.assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The <code>CSVParser</code> class parses a string that represents a csv file.
 * Each line in the string is parsed into a list of strings. At the end, the entire csv file is
 * parsed into a list of lists of strings.
 *
 * @author Shuwan Huang
 */
public class CSVParser implements ICSVParser {

  private static final String CSV_SPLIT_BY = "\",\""; // regular expression to split the line
  private List<List<String>> tokens;

  /**
   * Constructs a new CSVParser object. Parses the input csv string.
   * @param csv a string of a csv file
   */
  public CSVParser(String csv) {
    parseCSV(csv);
  }

  // for each line in the string, parses the line to tokens and stores as a list of strings.
  private void parseCSV(String csv) {
    tokens = new ArrayList<>();
    String[] lines = csv.split("\n");
    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];
      String[] values = line.substring(1, line.length() - 1).split(CSV_SPLIT_BY);
      tokens.add(Arrays.asList(values));
    }
  }

  /**
   * Returns a list of lists of string, the strings are tokens in the original csv file.
   * @return a list of lists of string
   */
  @Override
  public List<List<String>> getTokens() {
    return tokens;
  }

}
