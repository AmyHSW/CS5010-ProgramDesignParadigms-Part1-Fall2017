package edu.neu.ccs.cs5010;

import java.time.Duration;

/**
 * The <code>ExaminationRoom</code> class represents an examination room in the hospital.
 * When a patient is sent to this room, it will increase the busy time of this room by the treat time of patient,
 * and increment the number of patients treated in this room by one.
 *
 * The natural ordering of examination rooms is by the busy time. The room with less busy time has higher priority
 * to accept new patient.
 *
 * @author Shuwan Huang
 */
public class ExaminationRoom implements Comparable<ExaminationRoom> {

    private Duration busyTime; // the length of time when this room is busy
    private int nPatients; // number of patients treated in this room

    /**
     * Constructs a new ExaminationRoom object.
     * Sets the busy time and number of patients to be zero.
     */
    public ExaminationRoom() {
        busyTime = Duration.ZERO;
        nPatients = 0;
    }

    /**
     * Adds busy time to this examination room and increments the number of patients by one.
     * @param duration the length of treatment of one patient.
     */
    public void addBusyTime(Duration duration) {
        if (duration == null) {
            throw new IllegalArgumentException("duration is null.");
        }
        
        busyTime = busyTime.plus(duration);
        nPatients++;
    }

    /**
     * Return the busy time of this examination room.
     * @return the busy time of this examination room.
     */
    public Duration getBusyTime() {
        return busyTime;
    }

    /**
     * The ExaminationRoom with less busy time has higher priority.
     * @param other the other examination room to compare with.
     * @return a negative integer if this examination room is less busy than the other; the value 0 if they are
     *         equally busy; a positive integer if this examination room is busier than the other.
     */
    public int compareTo(ExaminationRoom other) {
        return this.busyTime.compareTo(other.busyTime);
    }

    /**
     * Compares this room to the specified room.
     *
     * @param other the other room.
     * @return true if this room equals <code>other</code>; false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        ExaminationRoom that = (ExaminationRoom) other;

        if (nPatients != that.nPatients) return false;
        return busyTime != null ? busyTime.equals(that.busyTime) : that.busyTime == null;
    }

    /**
     * Returns an integer hash code for this examination room.
     * @return an integer hash code for this examination room.
     */
    @Override
    public int hashCode() {
        int result = busyTime != null ? busyTime.hashCode() : 0;
        result = 31 * result + nPatients;
        return result;
    }

    /**
     * Rtuens a string representation of this examination room.
     * @return a string  representation of this examination room.
     */
    public String toString() {
        return "Examination room (busy for " + busyTime.toMinutes() + " min"
                + ", treated " + nPatients + " patients)";
    }
}
