package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;

public interface IExaminationRoom {

    /**
     * Adds busy time to this examination room and increments the number of patients by one.
     * @param busyTime the length of treatment of one patient.
     */
    void addBusyTime(Duration busyTime);

    /**
     * Return the busy time of this examination room.
     * @return the busy time of this examination room.
     */
    Duration getBusyTime();
}
