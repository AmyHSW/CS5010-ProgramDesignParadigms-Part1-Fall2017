package edu.neu.ccs.cs5010;

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
public class Patient implements Comparable<Patient>, ERSimulatorConstants {

    public static final Comparator<Patient> BY_DEPARTURE_TIME = new ByDepartureTime();

    private final LocalDateTime arrivalTime;
    private final int urgencyLevel;
    private final Duration treatTime;

    private LocalDateTime departureTime;
    private Duration waitTime;
    private ExaminationRoom room;

    /**
     * Constructs a new Patient with the provided arrivalTime, urgencyLevel, and treatTime.
     *
     * @param arrivalTime the time when Patient arrived.
     * @param urgencyLevel the urgency rating of Patient with 1 the highest priority and 10 the lowest.
     * @param treatTime the time it takes to treat the Patient.
     * @throws IllegalArgumentException if arrivalTime or treatTime is null, or urgency level is not between 1 and 10.
     */
    public Patient(LocalDateTime arrivalTime, int urgencyLevel, Duration treatTime) {
        if (arrivalTime == null) {
            throw new IllegalArgumentException("arrivalTime is null.");
        }

        if (treatTime == null) {
            throw new IllegalArgumentException("treatTime is null.");
        }

        if (urgencyLevel < MIN_URGENCY_LEVEL || urgencyLevel > MAX_URGENCY_LEVEL) {
            throw new IllegalArgumentException("urgency level (" + urgencyLevel + ") is not between 1 and 10.");
        }

        this.arrivalTime = arrivalTime;
        this.urgencyLevel = urgencyLevel;
        this.treatTime = treatTime;

        room = null;
        departureTime = LocalDateTime.MAX;
        waitTime = null;
    }

    // compare patients according to their departure time.
    private static class ByDepartureTime implements Comparator<Patient> {
        public int compare(Patient a, Patient b) {
            return b.departureTime.compareTo(a.departureTime);
        }
    }

    /**
     * Starts examination for the patient with the ExaminationRoom assigned and startTime set.
     * @param room the ExaminationRoom where patient gets treated.
     * @param startTime the time when patient starts examination.
     * @throws IllegalArgumentException if room is null.
     */
    public void startExamination(ExaminationRoom room, LocalDateTime startTime) {
        if (room == null) {
            throw new IllegalArgumentException("Examination room is null.");
        }
        validateStartTime(startTime);

        departureTime = startTime.plus(treatTime);
        waitTime = Duration.between(arrivalTime, startTime);
        this.room = room;
    }

    /**
     * @return the urgency level of the patient.
     */
    public int getUrgencyLevel() {
        return urgencyLevel;
    }

    /**
     * @return the ExaminationRoom where patient gets treated.
     */
    public ExaminationRoom getRoom() {
        return room;
    }

    /**
     * @return the departure time of the patient.
     */
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    /**
     * @return the time that patient has waited before getting treated.
     */
    public Duration getWaitTime() {
        return waitTime;
    }

    /**
     * Generates a new Patient object with random urgency level and treat time.
     * The arrival time is set to be the current local time.
     *
     * @return a new Patient object.
     * @throws Exception if the parameters to Patient constructor is invalid.
     */
    public static Patient next() {
        Random rd = new Random();
        int rUrgencyLevel, rNumMinutes;
        while (true) {
            rUrgencyLevel = rd.nextInt(MAX_URGENCY_LEVEL + 1);
            rNumMinutes = rd.nextInt(MAX_TREATMENT_MINUTES + 1);
            if (rUrgencyLevel >= MIN_URGENCY_LEVEL && rNumMinutes > 0) break;
        }
        return new Patient(LocalDateTime.now(), rUrgencyLevel, Duration.ofMinutes(rNumMinutes));
    }

    // validates the start time of examination so that it is always later than arrival time.
    private void validateStartTime(LocalDateTime startTime) {
        if (startTime == null) {
            throw new IllegalArgumentException("startTime is null.");
        }
        if (startTime.compareTo(arrivalTime) < 0) {
            throw new IllegalArgumentException("startTime is earlier than arrivalTime.");
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
        if (treatTime != null ? !treatTime.equals(patient.treatTime) : patient.treatTime != null) return false;
        if (departureTime != null ? !departureTime.equals(patient.departureTime) : patient.departureTime != null)
            return false;
        if (waitTime != null ? !waitTime.equals(patient.waitTime) : patient.waitTime != null) return false;
        return room != null ? room.equals(patient.room) : patient.room == null;
    }

    /**
     * Returns an integer hash code for this patient.
     * @return an integer hash code for this patient.
     */
    @Override
    public int hashCode() {
        int result = arrivalTime != null ? arrivalTime.hashCode() : 0;
        result = 31 * result + urgencyLevel;
        result = 31 * result + (treatTime != null ? treatTime.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (waitTime != null ? waitTime.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        return result;
    }

    /**
     * Rtuens a string representation of this patient.
     * @return a string  representation of this patient.
     */
    @Override
    public String toString() {
        return "Patient (arrived at " + arrivalTime
                + ", urgency level is " + urgencyLevel
                +", treatment time is " + treatTime.toMinutes() + " min)";
    }

}
