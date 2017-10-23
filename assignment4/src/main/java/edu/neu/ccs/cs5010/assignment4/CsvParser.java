package edu.neu.ccs.cs5010.assignment4;

import java.io.IOException;
import java.util.Arrays;

/**
 * The <code>CsvParse</code> class parses the csv file into a string array, where each string
 * represents a candy.
 *
 * @author Shuwan Huang
 */
public class CsvParser implements ICsvParser {

  private final String[] candies;

  /**
   * Constructs a new CsvParser with a csv file name. Converts the csv file to a string
   * using a method of IOLibrary, then parses the csv to a string array.
   * @param csvFile the csv file name
   * @throws IOException if there is an I/O failure
   */
  public CsvParser(String csvFile) throws IOException {
    String csvContent = IOLibrary.convertFileToString(csvFile);
    candies = parseCsv(csvContent);
  }

  private String[] parseCsv(String csvFile) {
    return csvFile.substring(0, csvFile.indexOf('\n')).split(", ");
  }

  /**
   * Returns a string array of candy names.
   * @return a string array of candy names.
   */
  @Override
  public String[] getCandyNames() {
    return Arrays.copyOf(candies, candies.length);
  }
}
