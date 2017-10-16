package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;

public class PresetGenerator implements IPatientGenerator {

  private static final int MIN_URGENCY_LEVEL = 1;
  private static final int MAX_URGENCY_LEVEL = 10;
  private final Duration treat;
  private final Duration pause;
  private int urgencyLevel = MAX_URGENCY_LEVEL;
  private int patientID = 0;

  private LocalDateTime lastTime;

  public PresetGenerator(Duration pause, Duration treat) {
    this.pause = pause;
    this.treat = treat;
  }

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
