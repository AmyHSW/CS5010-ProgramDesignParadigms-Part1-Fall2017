package edu.neu.ccs.cs5010.assignment2.section2;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * The <code>Patient</code> class represents a patient by arrival time, urgency level and treat time.
 * It also provides a static method next() to generate a new Patient with random parameters.
 * When the Patient starts examination, an ExaminationRoom will be set for the patient, and the departure
 * time can be calculated according to the start time of examination.
 * <p>
 * The natural order of Patient is by urgency levels; if two Patients have the same urgency level, they
 * will be ordered by arrival time.
 * <p>
 * Patient can also be ordered by departure time using the Comparator BY_DEPARTURE_TIME.
 *
 * @author Shuwan Huang
 */
public class Patient implements IPatient {

  /**
   * Compares patients according to their departure time.
   */
  public static final Comparator<IPatient> BY_DEPARTURE_TIME = new ByDepartureTime();

  private final LocalDateTime arrivalTime;
  private final Urgency urgency;
  private final Duration treatmentDuration;
  private final int id;

  private LocalDateTime departureTime;
  private Duration waitDuration;
  private IExaminationRoom room;

  /**
   * Constructs a new Patient with the provided arrivalTime, urgencyLevel, and treatmentDuration.
   *
   * @param arrivalTime       the time when Patient arrived.
   * @param urgency           the urgency rating of Patient with 1 the highest priority and 10 the lowest.
   * @param treatmentDuration the duration of treatment of this patient.
   * @throws IllegalArgumentException     if arrivalTime or treatmentDuration is null.
   * @throws InvalidUrgencyLevelException if urgency level is not between 1 to 10.
   */
  public Patient(LocalDateTime arrivalTime, Urgency urgency, Duration treatmentDuration, int id) {
    if (arrivalTime == null) {
      throw new IllegalArgumentException("arrivalTime is null.");
    }

    if (treatmentDuration == null) {
      throw new IllegalArgumentException("treatTime is null.");
    }

    this.arrivalTime = arrivalTime;
    this.urgency = urgency;
    this.treatmentDuration = treatmentDuration;
    this.id = id;

    room = null;
    departureTime = LocalDateTime.MAX;
    waitDuration = null;
  }

  // compare patients according to their departure time.
  private static class ByDepartureTime implements Comparator<IPatient>, Serializable {
    @Override
    public int compare(IPatient a, IPatient b) {
      return a.getDepartureTime().compareTo(b.getDepartureTime());
    }
  }

  /**
   * Starts examination for the patient with the ExaminationRoom assigned and startTime set.
   *
   * @param room      the ExaminationRoom where patient gets treated.
   * @param startTime the time when patient starts examination.
   * @throws IllegalArgumentException  if room or startTime is null.
   * @throws InvalidStartTimeException if start time is earlier than arrival time.
   */
  @Override
  public void startExamination(IExaminationRoom room, LocalDateTime startTime) {
    if (room == null) {
      throw new IllegalArgumentException("Examination room is null.");
    }
    if (startTime == null) {
      throw new IllegalArgumentException("startTime is null.");
    }
    if (startTime.compareTo(arrivalTime) < 0) {
      throw new InvalidStartTimeException("startTime is earlier than arrivalTime.");
    }

    departureTime = startTime.plus(treatmentDuration);
    waitDuration = Duration.between(arrivalTime, startTime);
    this.room = room;
  }

  /**
   * Gets the id of this patient.
   *
   * @return an integer number that is the id of this patient.
   */
  @Override
  public int getID() {
    return id;
  }

  /**
   * Gets the urgency level of this patient.
   *
   * @return an integer number that is the urgency level of this patient.
   */
  @Override
  public Urgency getUrgency() {
    return urgency;
  }

  /**
   * Gets the duration of treatment of this patient.
   *
   * @return the duration of treatment of this patient.
   */
  @Override
  public Duration getTreatmentDuration() {
    return treatmentDuration;
  }

  /**
   * Gets the ExaminationRoom where this patient gets treated.
   *
   * @return the ExaminationRoom where this patient gets treated.
   */
  @Override
  public IExaminationRoom getRoom() {
    return room;
  }

  /**
   * Returns the departure time of this patient.
   *
   * @return a LocalDateTime object that represents the departure time of the patient.
   */
  @Override
  public LocalDateTime getDepartureTime() {
    return departureTime;
  }

  /**
   * Returns the wait of this patient before getting treated.
   *
   * @return a Duration object that represents how long this patient has waited before getting treated.
   */
  @Override
  public Duration getWaitDuration() {
    return waitDuration;
  }

  /**
   * The patient with lower urgencyLevel has higher priority.
   * If two patients have same urgencyLevel, the one arrived earlier than the other
   * has higher priority.
   *
   * @param that the other Patient to compare.
   * @return a negative integer if this patient has higher priority and a positive integer if this has lower priority;
   * the value 0 if this patient and that have equal priority.
   */
  @Override
  public int compareTo(IPatient that) {
    if (this.urgency.compareTo(that.getUrgency()) != 0) {
      return this.urgency.compareTo(that.getUrgency());
    } else {
      return this.arrivalTime.compareTo(((Patient) that).arrivalTime);
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

    if (!urgency.equals(patient.urgency)) return false;
    if (!arrivalTime.equals(patient.arrivalTime)) return false;
    return treatmentDuration.equals(patient.treatmentDuration);
  }

  /**
   * Returns an integer hash code for this patient.
   *
   * @return an integer hash code for this patient.
   */
  @Override
  public int hashCode() {
    int result = arrivalTime.hashCode();
    result = 31 * result + urgency.hashCode();
    result = 31 * result + treatmentDuration.hashCode();
    return result;
  }

  /**
   * Returns a string representation of this patient.
   *
   * @return a string  representation of this patient.
   */
  @Override
  public String toString() {
    return "Patient (ID-" + id
        + "): Arrived at " + arrivalTime
        + ", urgency level is " + urgency.getLevel()
        + ", treatment duration is " + treatmentDuration.toMinutes() + " min.";
  }

}
