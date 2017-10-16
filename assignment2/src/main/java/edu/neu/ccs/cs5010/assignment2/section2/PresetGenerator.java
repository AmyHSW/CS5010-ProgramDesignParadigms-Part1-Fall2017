package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * The <code>PresetGenerator</code> is a patient generator that generates patients with the preset
 * parameters. For each Patient object, arrival time is the current local date time. The patient
 * ID will increment by 1 every time a patient is created.
 *
 * @author Shuwan Huang
 */
public class PresetGenerator implements IPatientGenerator {

  private static final int MIN_URGENCY_LEVEL = 1;
  private static final int MAX_URGENCY_LEVEL = 10;

  private final Duration treat;
  private final Duration pause;

  private int urgencyLevel = MAX_URGENCY_LEVEL;
  private int patientID = 0;

  private LocalDateTime lastTime;

  /**
   * Constructs a new PresetGenerator with the duration of pause time and duration of treatment
   * for each patient.
   * @param pause the pause between patients generation
   * @param treat the treatment duration
   */
  public PresetGenerator(Duration pause, Duration treat) {
    this.pause = pause;
    this.treat = treat;
  }

  /**
   * If the time difference between now and last time a patient was generated is over the pause
   * time, generates a new patient with the preset parameters. Otherwise, returns null.
   * @return a new patient or null depending on the time when this method is called
   */
  @Override
  public IPatient next() {
    if (patientID == 0) {
      lastTime = LocalDateTime.now();
      return new Patient(lastTime, new Urgency(urgencyLevel--), treat, ++patientID);
    }
    if (LocalDateTime.now().isAfter(lastTime.plus(pause))) {
      if (urgencyLevel < MIN_URGENCY_LEVEL) urgencyLevel = MAX_URGENCY_LEVEL;
      lastTime = LocalDateTime.now();
      return new Patient(lastTime, new Urgency(urgencyLevel--), treat, ++patientID);
    }
    return null;
  }
}
