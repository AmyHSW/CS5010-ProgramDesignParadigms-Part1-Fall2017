package edu.neu.ccs.cs5010.assignment2.section2;

import org.junit.Test;

import java.time.Duration;

/**
 * The <code>ERSimulatorTest</code> is a test class for <code>ERSimulator</code>.
 *
 * @author Shuwan Huang
 */
public class ERSimulatorTest {

  /**
   * Tests that ERSimulator constructor throws an InvalidNumRoomException if numRoom is not positive.
   */
  @Test(expected = InvalidNumRoomException.class)
  public void testThrowsInvalidNumRoomException() throws Exception {
    ERSimulator other = new ERSimulator(-1);
  }

  /**
   * Tests the runSimulation method.
   */
  @Test
  public void testRunSimulation() throws Exception {
    IERSimulator simulator = new ERSimulator(1);
    IPatientGenerator patientGenerator = new PresetGenerator(Duration.ofSeconds(5), Duration.ofSeconds(5));
    simulator.runSimulation(Duration.ofSeconds(10), patientGenerator);
  }

  @Test
  public void testMain() throws Exception {
    String[] args = {
        "--simulation-time", "1",
        "--mode", "1",
        "--max-pause", "1",
        "--max-treatment", "1",
        "--number-rooms", "5"
    };
    ERSimulator.main(args);

    String[] args2 = {
        "--simulation-time", "1",
        "--mode", "2",
        "--max-pause", "1",
        "--max-treatment", "1",
        "--number-rooms", "5"
    };
    ERSimulator.main(args2);
  }

  @Test(expected = InvalidInputFormatException.class)
  public void expectedInvalidInputFormatException() throws Exception {
    String[] args = {
        "--simulation-time", "5",
        "--mode", "1",
        "--max-pause", "1",
        "--max-treatment"
    };
    ERSimulator.main(args);
  }
}