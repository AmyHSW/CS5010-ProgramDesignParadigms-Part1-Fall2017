package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * The <code>RandomGenerator</code> is a patient generator that generates a new Patient object
 * when the next method is called. The pause between generation of patients is randomly selected.
 * For each Patient object, arrival time is the current local date time, urgency and treatment
 * time are randomly selected. The patient ID will increment by 1 every time a patient is created.
 *
 * @author Shuwan Huang
 */
public class RandomGenerator implements IPatientGenerator {

  private static final int MIN_URGENCY_LEVEL = 1;
  private static final int MAX_URGENCY_LEVEL = 10;

  private final int maxPauseMin;
  private final int maxTreatmentMin;

  private int patientID = 0;
  private Duration pause; // pause between generation of patients
  private LocalDateTime lastTime; // last time a new patient was generated

  /**
   * Constructs a new RandomGenerator with the duration of max pause and duration of
   * max treatment.
   * @param maxPause the max pause between patients generation
   * @param maxTreatment the max treatment length of patients
   */
  public RandomGenerator(Duration maxPause, Duration maxTreatment) {
    maxPauseMin = (int) maxPause.toMinutes();
    maxTreatmentMin = (int) maxTreatment.toMinutes();
  }

  /**
   * Generates a new Patient object with random urgency and treatment duration.
   * The arrival time is set to be the current local time.
   *
   * @return a new Patient object; null if not ready to generate a patient
   */
  public IPatient next() {
    if (patientID > 0 && LocalDateTime.now().isBefore(lastTime.plus(pause))) {
      return null;
    }
    int urgencyLevel, treatmentMin;
    Random rd = new Random();
    while (true) {
      urgencyLevel = rd.nextInt(MAX_URGENCY_LEVEL + 1);
      treatmentMin = rd.nextInt(maxTreatmentMin + 1);
      if (urgencyLevel >= MIN_URGENCY_LEVEL && treatmentMin > 0) break;
    }
    lastTime = LocalDateTime.now();
    pause = Duration.ofMinutes((new Random()).nextInt(maxPauseMin + 1));
    return new Patient(lastTime, new Urgency(urgencyLevel),
                       Duration.ofMinutes(treatmentMin), ++patientID);
  }

}
