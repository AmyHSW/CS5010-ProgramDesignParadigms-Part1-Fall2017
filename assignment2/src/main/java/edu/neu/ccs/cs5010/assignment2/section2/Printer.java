package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.LocalDateTime;

public class Printer implements IPrinter {

  @Override
  public void printNewPatient(IPatient patient) {
    System.out.println((char) 27 + "[31mAdded to line: " + (char) 27 + "[0m" + patient);
  }

  @Override
  public void printFinishMsg(LocalDateTime now, int patientID) {
    System.out.println(now.toLocalTime());
    System.out.println((char) 27 + "[34mFinished treating patient (ID-" + patientID + ")" + (char) 27 + "[0m");
  }

  @Override
  public void printStartMsg(LocalDateTime now, int patientID) {
    System.out.println((char) 27 + "[32mStarted treating patient (ID-" + patientID + ")" + (char) 27 + "[0m");
  }

  @Override
  public void printResults(String results) {
    System.out.println(results);
  }

}
