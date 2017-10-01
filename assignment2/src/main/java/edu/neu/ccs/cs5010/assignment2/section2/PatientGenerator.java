package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * The <code>PatientGenerator</code> generates a new Patient object when the next method is called.
 * The pause between generation of patients is randomly selected.
 * For each Patient object, arrival time is the current local date time, urgency level and treatment time
 * are randomly selected. The patient ID will increment by 1 every time a patient is created.
 *
 * @author Shuwan Huang
 */
public class PatientGenerator implements ERSimulatorConstants {

    private int patientID; // starting at 1
    private final Random rd; // a Random object to produce random parameters
    private int numPauseMillis; // number of milli-seconds to pause between generation of patients
    protected LocalDateTime lastTime; // last time a new patient was generated; set protected only for testing!!

    /**
     * Constructs a new PatientGenerator and sets the initial patient id to be 1.
     */
    public PatientGenerator() {
        patientID = 1;
        rd = new Random();
        lastTime = LocalDateTime.now();
        numPauseMillis = rd.nextInt(MAX_PAUSE_ADD_PATIENT + 1);
    }

    /**
     * Generates a new Patient object with random urgency level and treatment duration.
     * The arrival time is set to be the current local time.
     *
     * @return a new Patient object.
     */
    public Patient next() {
        if (Duration.between(lastTime, LocalDateTime.now()).toMillis() < numPauseMillis) {
            return null;
        }
        int urgencyLevel, treatmentMinutes;
        while (true) {
            urgencyLevel = rd.nextInt(MAX_URGENCY_LEVEL + 1);
            treatmentMinutes = rd.nextInt(MAX_TREATMENT_MINUTES + 1);
            if (urgencyLevel >= MIN_URGENCY_LEVEL && treatmentMinutes > 0) break;
        }
        lastTime = LocalDateTime.now();
        numPauseMillis = rd.nextInt(MAX_PAUSE_ADD_PATIENT + 1);
        return new Patient(lastTime, urgencyLevel, Duration.ofMinutes(treatmentMinutes), patientID++);
    }

}
