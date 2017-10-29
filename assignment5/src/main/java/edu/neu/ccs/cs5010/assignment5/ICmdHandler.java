package edu.neu.ccs.cs5010.assignment5;

public interface ICmdHandler {

  boolean isValid();

  String getErrorMessage();

  int getNumClients();

  int getNumVerifications();

  double getFraction();

  String getOutputDir();
}
