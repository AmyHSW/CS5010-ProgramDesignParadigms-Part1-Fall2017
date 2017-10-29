package edu.neu.ccs.cs5010.assignment5;

import java.io.IOException;

public interface ISecureBankVerificationSimulator {

  void processVerifications();

  void outputResults() throws IOException;

}
