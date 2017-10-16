package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;

/**
 * The <code>IERSimulator</code> interface represents the emergency room simulator.
 *
 * @author Shuwan Huang
 */
public interface IERSimulator {

  /**
   * Starts the simulation. Keeps generating patients during the given duration
   * of simulation using the patient generator.
   *
   * @param simulationTime   the duration of time for generating patients.
   * @param patientGenerator a patient generator
   */
  void runSimulation(Duration simulationTime, IPatientGenerator patientGenerator);

}
