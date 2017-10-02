package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * The <code>IERSimulator</code> interface represents the emergency room simulator.
 *
 * @author Shuwan Huang
 */
public interface IERSimulator {

    /**
     * Adds a patient to this simulator.
     * @param patient the patient to be added.
     */
    void addPatient(IPatient patient);

    /**
     * Checks if all the patients have been treated.
     * @return true if all the patients have finished treatment; false otherwise.
     */
    boolean isFree();

    /**
     * Updates events in emergency room system to the current local time.
     */
    void update();

    /**
     * Runs the simulation at given start time, keeps generating patients during the given duration
     * of simulation and updating the simulation as it goes. Sets the max pause milliseconds and max treatment
     * minutes as well.
     * @param startTime the start time of this simulation.
     * @param simulationTime the duration of time for generating patients.
     * @param maxPause the max pause of milliseconds between patient generation
     * @param maxTreatment the max treatment (minutes) of patient.
     */
    void runSimulation(LocalDateTime startTime, Duration simulationTime, int maxPause, int maxTreatment);

}
