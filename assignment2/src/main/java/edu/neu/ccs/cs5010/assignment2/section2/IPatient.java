package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * The <code>IPatient</code> interface represents a patient object.
 *
 * @author Shuwan Huang
 */
public interface IPatient {

    /**
     * Starts examination for the patient with the ExaminationRoom assigned and startTime set.
     *
     * @param room the ExaminationRoom where patient gets treated.
     * @param start the time when patient starts examination.
     */
    void startExamination(ExaminationRoom room, LocalDateTime start);

    /**
     * Gets the id of this patient.
     * @return an integer number that is the id of this patient.
     */
    int getID();

    /**
     * Gets the urgency level of this patient.
     * @return an integer number that is the urgency level of this patient.
     */
    int getUrgencyLevel();

    /**
     * Gets the duration of treatment of this patient.
     * @return the duration of treatment of this patient.
     */
    Duration getTreatmentDuration();

    /**
     * Gets the ExaminationRoom where this patient gets treated.
     * @return the ExaminationRoom where this patient gets treated.
     */
    ExaminationRoom getRoom();

    /**
     * Returns the departure time of this patient.
     * @return a LocalDateTime object that represents the departure time of the patient.
     */
    LocalDateTime getDepartureTime();

    /**
     * Returns the wait of this patient before getting treated.
     * @return a Duration object that represents how long this patient has waited before getting treated.
     */
    Duration getWaitDuration();
}
