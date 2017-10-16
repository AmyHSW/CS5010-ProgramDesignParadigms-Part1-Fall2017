package edu.neu.ccs.cs5010.assignment3;

import java.util.List;

/**
 * The <code>IPassengerInfo</code> interface represents the passenger information.
 *
 * @author Shuwan Huang
 */
public interface IPassengerInfo {

  /**
   * Returns the headers in csv file.
   * @return headers in csv file
   */
  List<String> getHeaders();

  /**
   * Returns true if there exists next passenger.
   * @return true if there exists next passenger, false otherwise
   */
  boolean hasNextPassenger();

  /**
   * Returns a list of string that contains information of next passenger.
   * @return a list of string that contains information of next passenger.
   */
  List<String> nextPassenger();
}
