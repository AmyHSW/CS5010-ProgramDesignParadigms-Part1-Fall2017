package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * The <code>PatientGenerator</code> generates a new Patient object when the next method is called.
 * For each Patient object, arrival time is the current local date time, urgency level and treatment time
 * are randomly selected. The patient ID will increment by 1 every time a patient is created.
 *
 * @author Shuwan Huang
 */
public class PatientGenerator implements ERSimulatorConstants {

    private int patientID;

    /**
     * Constructs a new PatientGenerator and sets the initial patient id to be 1.
     */
    public PatientGenerator() {
        patientID = 1;
    }

    /**
     * Generates a new Patient object with random urgency level and treat time.
     * The arrival time is set to be the current local time.
     *
     * @return a new Patient object.
     */
    public Patient next() {
        Random rd = new Random();
        int rUrgencyLevel, rNumMinutes;
        while (true) {
            rUrgencyLevel = rd.nextInt(MAX_URGENCY_LEVEL + 1);
            rNumMinutes = rd.nextInt(MAX_TREATMENT_MINUTES + 1);
            if (rUrgencyLevel >= MIN_URGENCY_LEVEL && rNumMinutes > 0) break;
        }
        return new Patient(LocalDateTime.now(), rUrgencyLevel, Duration.ofMinutes(rNumMinutes), patientID++);
    }

}
