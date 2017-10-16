package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * The <code>IPrinter</code> interface represents a printer.
 *
 * @author Shuwan Huang
 */
public interface IPrinter {

  /**
   * Prints to console the addition of new patient.
   * @param patient a new patient
   */
  void printNewPatient(IPatient patient);

  /**
   * Prints to console that a patient has finished treatment.
   * @param time the finish time of treatment
   * @param patientID the id of the patient
   */
  void printFinishExamMsg(LocalDateTime time, int patientID);

  /**
   * Prints to console that a patient has started treatment.
   * @param time the start time of treatment
   * @param patientID the id of patient
   */
  void printStartExamMsg(LocalDateTime time, int patientID);

  /**
   * Prints a message to console.
   * @param msg a string
   */
  void printMsg(String msg);

  /**
   * Prints to console that the simulation has started.
   * @param time the start time of simulation
   */
  void printStartMsg(LocalDateTime time);

  /**
   * Prints to console that the simulation has ended.
   * @param runtime the actual duration of this simulation.
   */
  void printEndMsg(Duration runtime);

  /**
   * Prints to console that the simulator is busy.
   */
  void printBusyMsg();
}
