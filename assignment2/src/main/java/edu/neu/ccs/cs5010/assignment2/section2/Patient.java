package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Random;

/**
 * The <code>Patient</code> class represents a patient by arrival time, urgency level and treat time.
 * It also provides a static method next() to generate a new Patient with random parameters.
 * When the Patient starts examination, an ExaminationRoom will be set for the patient, and the departure
 * time can be calculated according to the start time of examination.
 *
 * The natural order of Patient is by urgency levels; if two Patients have the same urgency level, they
 * will be ordered by arrival time.
 *
 * Patient can also be ordered by departure time using the Comparator BY_DEPARTURE_TIME.
 *
 * @author Shuwan Huang
 */
public class Patient implements IPatient, Comparable<Patient>, ERSimulatorConstants {

    /**
     * Compares patients according to their departure time.
     */
    public static final Comparator<Patient> BY_DEPARTURE_TIME = new ByDepartureTime();

    private final LocalDateTime arrivalTime;
    private final int urgencyLevel;
    private final Duration treatmentDuration;
    private final int id;

    private LocalDateTime departureTime;
    private Duration waitDuration;
    private ExaminationRoom room;

    /**
     * Constructs a new Patient with the provided arrivalTime, urgencyLevel, and treatTime.
     *
     * @param arrivalTime the time when Patient arrived.
     * @param urgencyLevel the urgency rating of Patient with 1 the highest priority and 10 the lowest.
     * @param treatTime the time it takes to treat the Patient.
     * @throws IllegalArgumentException if arrivalTime or treatTime is null, or urgency level is not between 1 and 10.
     */
    public Patient(LocalDateTime arrivalTime, int urgencyLevel, Duration treatTime, int id) {
        if (arrivalTime == null) {
            throw new IllegalArgumentException("arrivalTime is null.");
        }

        if (treatTime == null) {
            throw new IllegalArgumentException("treatTime is null.");
        }

        if (urgencyLevel < MIN_URGENCY_LEVEL || urgencyLevel > MAX_URGENCY_LEVEL) {
            throw new InvalidUrgencyLevelException("urgency level (" + urgencyLevel + ") is not between 1 and 10.");
        }

        this.arrivalTime = arrivalTime;
        this.urgencyLevel = urgencyLevel;
        this.treatmentDuration = treatTime;
        this.id = id;

        room = null;
        departureTime = LocalDateTime.MAX;
        waitDuration = null;
    }

    // compare patients according to their departure time.
    private static class ByDepartureTime implements Comparator<Patient> {
        @Override
        public int compare(Patient a, Patient b) {
            return a.departureTime.compareTo(b.departureTime);
        }
    }

    /**
     * Starts examination for the patient with the ExaminationRoom assigned and startTime set.
     *
     * @param room the ExaminationRoom where patient gets treated.
     * @param startTime the time when patient starts examination.
     * @throws IllegalArgumentException if room is null.
     */
    @Override
    public void startExamination(ExaminationRoom room, LocalDateTime startTime) {
        if (room == null) {
            throw new IllegalArgumentException("Examination room is null.");
        }
        validateStartTime(startTime);

        departureTime = startTime.plus(treatmentDuration);
        waitDuration = Duration.between(arrivalTime, startTime);
        this.room = room;
    }

    /**
     * Gets the id of this patient.
     * @return an integer number that is the id of this patient.
     */
    @Override
    public int getID() {
        return id;
    }

    /**
     * Gets the urgency level of this patient.
     * @return an integer number that is the urgency level of this patient.
     */
    @Override
    public int getUrgencyLevel() {
        return urgencyLevel;
    }

    /**
     * Gets the duration of treatment of this patient.
     * @return the duration of treatment of this patient.
     */
    @Override
    public Duration getTreatmentDuration() {
        return treatmentDuration;
    }

    /**
     * Gets the ExaminationRoom where this patient gets treated.
     * @return the ExaminationRoom where this patient gets treated.
     */
    @Override
    public ExaminationRoom getRoom() {
        return room;
    }

    /**
     * Returns the departure time of this patient.
     * @return a LocalDateTime object that represents the departure time of the patient.
     */
    @Override
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    /**
     * Returns the wait of this patient before getting treated.
     * @return a Duration object that represents how long this patient has waited before getting treated.
     */
    @Override
    public Duration getWaitDuration() {
        return waitDuration;
    }

    // validates the start time of examination so that it is always later than arrival time.
    private void validateStartTime(LocalDateTime startTime) {
        if (startTime == null) {
            throw new IllegalArgumentException("startTime is null.");
        }
        if (startTime.compareTo(arrivalTime) < 0) {
            throw new InvalidStartTimeException("startTime is earlier than arrivalTime.");
        }
    }

    /**
     * The patient with lower urgencyLevel has higher priority.
     * If two patients have same urgencyLevel, the one arrived earlier than the other
     * has higher priority.
     * @param that the other Patient to compare.
     * @return a negative integer if this patient has higher priority and a positive integer if this has lower priority;
     *         the value 0 if this patient and that have equal priority.
     */
    @Override
    public int compareTo(Patient that) {
        if (this.urgencyLevel != that.urgencyLevel) {
            return this.urgencyLevel - that.urgencyLevel;
        } else {
            return this.arrivalTime.compareTo(that.arrivalTime);
        }
    }

    /**
     * Compares this patient to the specified patient.
     *
     * @param other the other patient.
     * @return true if this patient equals <code>other</code>; false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Patient patient = (Patient) other;

        if (urgencyLevel != patient.urgencyLevel) return false;
        if (arrivalTime != null ? !arrivalTime.equals(patient.arrivalTime) : patient.arrivalTime != null) return false;
        return treatmentDuration != null ? treatmentDuration.equals(patient.treatmentDuration) : patient.treatmentDuration == null;
    }

    /**
     * Returns an integer hash code for this patient.
     * @return an integer hash code for this patient.
     */
    @Override
    public int hashCode() {
        int result = arrivalTime != null ? arrivalTime.hashCode() : 0;
        result = 31 * result + urgencyLevel;
        result = 31 * result + (treatmentDuration != null ? treatmentDuration.hashCode() : 0);
        return result;
    }

    /**
     * Returns a string representation of this patient.
     * @return a string  representation of this patient.
     */
    @Override
    public String toString() {
        return "Patient (ID-" + id
                + "): Arrived at " + arrivalTime
                + ", urgency level is " + urgencyLevel
                +", treatment duration is " + treatmentDuration.toMinutes() + " min.";
    }

}
