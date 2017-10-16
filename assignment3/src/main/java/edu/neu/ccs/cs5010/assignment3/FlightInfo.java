package edu.neu.ccs.cs5010.assignment3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>FlightInfo</code> class provides the flight information including the source city,
 * destination city and the event details.
 *
 * @author Shuwan Huang
 */
public class FlightInfo implements IFlightInfo {

  private String sourceCity;
  private String destinCity;
  private String event;

  /**
   * Constructs a new FlightInfo object with a cmd handler. The flight info is obtained by the
   * csv file name and the event details given by cmd handler.
   * @param cmdHandler a CMDHandler that provides flight info
   */
  public FlightInfo(ICMDHandler cmdHandler) {
    parseFilename(cmdHandler.getCsvFile());
    event = cmdHandler.getEvent();
  }

  private void parseFilename(String file) {
    Pattern pattern = Pattern.compile("Flight[0-9]+From([A-Z][a-z]+)To([A-Z][a-z]+).csv");
    Matcher matcher = pattern.matcher(file);
    if (matcher.matches()) {
      sourceCity = matcher.group(1);
      destinCity = matcher.group(2);
    }
  }

  /**
   * Returns the departure city of this flight.
   * @return the departure city.
   */
  @Override
  public String getSourceCity() {
    return sourceCity;
  }

  /**
   * Returns the destination city of this flight.
   * @return the destination city.
   */
  @Override
  public String getDestinationCity() {
    return destinCity;
  }

  /**
   * Returns the event details.
   * @return the event details.
   */
  @Override
  public String getEvent() {
    return event;
  }

}
