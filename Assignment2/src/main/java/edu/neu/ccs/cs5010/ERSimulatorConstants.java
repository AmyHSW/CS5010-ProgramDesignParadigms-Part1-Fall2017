package edu.neu.ccs.cs5010;

import java.time.Duration;

/**
 * The <code>ERSimulatorConstants</code> interface provides all constants that may be used in the emergency room
 * simulator project.
 *
 * @author Shuwan Huang
 */
public interface ERSimulatorConstants {

    public static final Duration SIMULATION_MAX_TIME = Duration.ofMinutes(60);

    public static final int PAUSE_MILLI_SECONDS = 180000;

    public static final int N_ROOMS = 5;

    public static final int MAX_TREATMENT_MINUTES = 10;

    public static final int MIN_URGENCY_LEVEL = 1;

    public static final int MAX_URGENCY_LEVEL = 10;

}
