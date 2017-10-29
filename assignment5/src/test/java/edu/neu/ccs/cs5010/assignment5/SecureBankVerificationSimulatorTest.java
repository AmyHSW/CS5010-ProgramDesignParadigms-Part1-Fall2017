package edu.neu.ccs.cs5010.assignment5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SecureBankVerificationSimulatorTest {

  private ISecureBankVerificationSimulator simulator;

  @Before
  public void setup() throws Exception {
    simulator = new SecureBankVerificationSimulator(10, 10, 0, "test1.csv");
  }

  @Test
  public void processVerifications() throws Exception {
    simulator.processVerifications();
  }

  @Test
  public void outputResults() throws Exception {
    simulator.outputResults();
  }

  @Test
  public void testEquals() throws Exception {
    assertTrue(!simulator.equals(null));
    assertTrue(simulator.equals(simulator));
    assertTrue(!simulator.equals(""));
    assertTrue(!simulator.equals(new SecureBankVerificationSimulator(10, 10, 0, null)));
    assertTrue(!simulator.equals(new SecureBankVerificationSimulator(10, 1, 0, "test1.csv")));
    ISecureBankVerificationSimulator simulator1 =
        new SecureBankVerificationSimulator(0, 0, 0, "test1.csv");
    ISecureBankVerificationSimulator simulator2 =
        new SecureBankVerificationSimulator(0, 0, 0, "test1.csv");
    assertTrue(simulator1.equals(simulator2));
  }

  @Test
  public void testHashCode() throws Exception {
    ISecureBankVerificationSimulator simulator1 =
        new SecureBankVerificationSimulator(10, 10, 0, null);
    assertTrue(simulator.hashCode() != simulator1.hashCode());
  }

  @Test
  public void main() throws Exception {
    String[] args = { "50000", "10000", "0.2", "test.csv"};
    SecureBankVerificationSimulator.main(args);
  }

  @Test(expected = InvalidInputException.class)
  public void expetedInvalidInputException() throws Exception {
    String[] args = { "10", "5", "0.1"};
    SecureBankVerificationSimulator.main(args);
  }
}