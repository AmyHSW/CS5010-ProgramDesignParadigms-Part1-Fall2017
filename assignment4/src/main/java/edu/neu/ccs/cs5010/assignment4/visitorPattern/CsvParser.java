package edu.neu.ccs.cs5010.assignment4.visitorPattern;

import java.io.IOException;
import java.util.Arrays;

public class CsvParser implements ICsvParser {

  private final String[] candies;

  public CsvParser(String csvFile) throws IOException {
    String csvContent = IOLibrary.convertFileToString(csvFile);
    candies = parseCsv(csvContent);
  }

  private String[] parseCsv(String csvFile) {
    return csvFile.substring(0, csvFile.indexOf('\n')).split(", ");
  }

  @Override
  public String[] getCandyNames() {
    return Arrays.copyOf(candies, candies.length);
  }
}
