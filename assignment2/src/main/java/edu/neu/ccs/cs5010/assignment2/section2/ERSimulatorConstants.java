package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;

/**
 * The <code>ERSimulatorConstants</code> interface provides all constants that may be used in the emergency room
 * simulator project.
 *
 * @author Shuwan Huang
 */
public interface ERSimulatorConstants {

    /**
     * The max duration of time that program keeps adding new patients.
     */
    Duration SIMULATION_MAX_TIME = Duration.ofHours(8);

    /**
     * An integer that represents the number of milli-seconds that program waits before adding
     * another new patient.
     */
    int PAUSE_ADD_PATIENT = 60000;

    /**
     * An integer that represents the number of milli-seconds that program waits before updating
     * the ongoing examination queue and patients queue.
     */
    int PAUSE_BETWEEN_UPDATE = 60000;

    /**
     * The max number of minutes that a patient may need to get treated.
     */
    int MAX_TREATMENT_MINUTES = 10;

    /**
     * The min urgency level.
     */
    int MIN_URGENCY_LEVEL = 1;

    /**
     * The max urgency level.
     */
    int MAX_URGENCY_LEVEL = 10;

}
