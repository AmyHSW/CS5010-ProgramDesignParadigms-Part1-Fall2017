package edu.neu.ccs.cs5010.assignment5;

import java.io.IOException;

/**
 * The ISecureBankVerificationSimulator interface represents a secure bank verification
 * simulator.
 *
 * @author Shuwan Huang
 */
public interface ISecureBankVerificationSimulator {

  /**
   * Processes the verifications by sending client requests to the bank and gathers
   * the transaction information.
   */
  void processVerifications();

  /**
   * Stores the results of simulation to a csv file, as well as prints to console some
   * simulation information.
   * @throws IOException if there exists an I/O failure
   */
  void outputResults() throws IOException;

}
