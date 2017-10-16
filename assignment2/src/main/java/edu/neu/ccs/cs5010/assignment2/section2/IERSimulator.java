package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;

/**
 * The <code>IERSimulator</code> interface represents the emergency room simulator.
 *
 * @author Shuwan Huang
 */
public interface IERSimulator {

    /**
     * Runs the simulation at given start time, keeps generating patients during the given duration
     * of simulation and updating the simulation as it goes. Sets the max pause milliseconds and max treatment
     * minutes as well.
     * @param simulationTime the duration of time for generating patients.
     */
    void runSimulation(Duration simulationTime, IPatientGenerator patientGenerator);

}
