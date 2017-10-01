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
     * The max number of milli-seconds to pause between generation of new patients.
     */
    int MAX_PAUSE_ADD_PATIENT = 60000;

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
