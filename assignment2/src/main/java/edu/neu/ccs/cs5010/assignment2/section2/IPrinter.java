package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.LocalDateTime;

public interface IPrinter {

  void printNewPatient(IPatient patient);

  void printFinishMsg(LocalDateTime now, int patientID);

  void printStartMsg(LocalDateTime now, int patientID);

  void printResults(String results);
}
