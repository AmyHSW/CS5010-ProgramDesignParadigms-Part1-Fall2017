package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;

public interface ICMDHandler {

  boolean isLegalFormat();

  String getErrorMessage();

  int getMode();

  Duration getSimulationTime();

  Duration getMaxTreatment();

  Duration getMaxPause();

  int getNumRooms();
}
