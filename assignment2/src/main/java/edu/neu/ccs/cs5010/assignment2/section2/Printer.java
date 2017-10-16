package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * The <code>Printer</code> class provides methods to print message to console during and
 * after the simulation.
 *
 * @author Shuwan Huang
 */
public class Printer implements IPrinter {

  private static final char ESCAPE = (char)27;

  /**
   * Prints to console the addition of new patient.
   * @param patient a new patient
   */
  @Override
  public void printNewPatient(IPatient patient) {
    System.out.println(ESCAPE + "[31mAdded to line: " + ESCAPE + "[0m" + patient);
  }

  /**
   * Prints to console that a patient has finished treatment.
   * @param time the finish time of treatment
   * @param patientID the id of the patient
   */
  @Override
  public void printFinishExamMsg(LocalDateTime time, int patientID) {
    System.out.println(time.toLocalTime());
    System.out.println(ESCAPE + "[34mFinished treating patient (ID-"
                       + patientID + ")" + ESCAPE + "[0m");
  }

  /**
   * Prints to console that a patient has started treatment.
   * @param time the start time of treatment
   * @param patientID the id of patient
   */
  @Override
  public void printStartExamMsg(LocalDateTime time, int patientID) {
    System.out.println(time.toLocalTime());
    System.out.println(ESCAPE + "[32mStarted treating patient (ID-"
                       + patientID + ")" + ESCAPE + "[0m");
  }

  /**
   * Prints a message to console.
   * @param msg a string
   */
  @Override
  public void printMsg(String msg) {
    System.out.println(msg);
  }

  /**
   * Prints to console that the simulation has started.
   * @param time the start time of simulation
   */
  @Override
  public void printStartMsg(LocalDateTime time) {
    System.out.println("Starts emergency room simulation at " + time.toLocalTime() + ":");
  }

  /**
   * Prints to console that the simulation has ended.
   * @param runTime the actual duration of this simulation.
   */
  @Override
  public void printEndMsg(Duration runTime) {
    System.out.println("The simulation is over. It has run " + runTime.toMinutes() + " min.");
    System.out.println("Now analyzing the results..");
  }

  /**
   * Prints to console that the simulator is busy.
   */
  @Override
  public void printBusyMsg() {
    System.out.print("This simulator is already busy. Please wait.");
  }
}
