package edu.neu.ccs.cs5010.assignment3;

/**
 * The <code>IFlightInfo</code> interface represents the flight information.
 *
 * @author Shuwan Huang
 */
public interface IFlightInfo {

  /**
   * Returns the departure city of this flight.
   * @return the departure city.
   */
  String getSourceCity();

  /**
   * Returns the destination city of this flight.
   * @return the destination city.
   */
  String getDestinationCity();

  /**
   * Returns the event details.
   * @return the event details.
   */
  String getEvent();
}
