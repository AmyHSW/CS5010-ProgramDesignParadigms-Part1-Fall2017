package edu.neu.ccs.cs5010.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * The <code>PassengerInfo</code> class stores all passenger information provided by the
 * csv file. The csv file is parsed into lists of tokens by a csv parser. The first list
 * is the headers, and the rest are passenger information.
 *
 * @author Shuwan Huang
 */
public class PassengerInfo implements IPassengerInfo {

  private int psID = 0; // records the number of passenger called by nextPassenger()
  private List<String> headers;
  private List<List<String>> passengers;

  /**
   * Parses the csv file using a csv parser. Builds passenger information based on the
   * results of csv parser.
   *
   * @param csvFileName the name of a csv file
   */
  public PassengerInfo(String csvFileName) throws IOException {
    CSVParser csvParser = new CSVParser(IOLibrary.convertFileToString(csvFileName));
    buildPassengerInfo(csvParser.getTokens());
  }

  private void buildPassengerInfo(List<List<String>> tokens) {
    headers = tokens.get(0);
    passengers = tokens.subList(1, tokens.size());
  }

  /**
   * Returns the headers in csv file.
   * @return headers in csv file
   */
  @Override
  public List<String> getHeaders() {
    return headers;
  }

  /**
   * Returns true if there exists next passenger.
   * @return true if there exists next passenger, false otherwise
   */
  @Override
  public boolean hasNextPassenger() {
    return psID < passengers.size();
  }

  /**
   * Returns a list of string that contains information of next passenger.
   * @return a list of string that contains information of next passenger.
   */
  @Override
  public List<String> nextPassenger() {
    return passengers.get(psID++);
  }

}
