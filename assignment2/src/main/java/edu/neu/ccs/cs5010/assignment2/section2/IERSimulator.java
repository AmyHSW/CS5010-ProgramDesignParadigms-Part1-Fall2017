package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;

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
     * Analyzes and prints the average wait time for treatment to console.
     */
    void reportPatientsAvrgWaitAndTreatment();

    /**
     * Analyzes and prints the usage of examination rooms to console.
     * @param duration the duration of the simulation.
     */
    void reportRoomUsage(Duration duration);
}
