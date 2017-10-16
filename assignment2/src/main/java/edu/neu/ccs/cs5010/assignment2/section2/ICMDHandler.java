package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;

/**
 * The <code>ICMDHandler</code> interface represents a cmd handler.
 *
 * @author Shuwan Huang
 */
public interface ICMDHandler {

  /**
   * Returns true if the required information are provided in legal format.
   * @return true if arguments are in legal format and false otherwise.
   */
  boolean isLegalFormat();


  /**
   * Returns the error message if arguments are in illegal format; null if arguments are in
   * legal format.
   *
   * @return the error message if arguments are in illegal format; null if arguments are in
   * legal format.
   */
  String getErrorMessage();

  /**
   * Returns the mode of simulation; -1 if arguments are not in legal format.
   * @return the mode of simulation; -1 if arguments are not in legal format.
   */
  int getMode();

  /**
   * Returns the duration of simulation; null if arguments are not in legal format.
   * @return the duration of simulation; null if arguments are not in legal format.
   */
  Duration getSimulationTime();

  /**
   * Returns the max duration of treatment; null if arguments are not in legal format
   * @return the max duration of treatment; null if arguments are not in legal format
   */
  Duration getMaxTreatment();

  /**
   * Returns the max pause between patients generation; null if arguments are not in legal format
   * @return the max pause between patients generation; null if arguments are not in legal format
   */
  Duration getMaxPause();

  /**
   * Returns the number of rooms; -1 if arguments are not in legal format.
   * @return the number of rooms; -1 if arguments are not in legal format.
   */
  int getNumRooms();
}
