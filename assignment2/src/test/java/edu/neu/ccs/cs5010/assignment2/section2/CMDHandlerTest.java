package edu.neu.ccs.cs5010.assignment2.section2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CMDHandlerTest {

  private ICMDHandler cmdHandler1 = null;
  private ICMDHandler cmdHandler2 = null;
  private ICMDHandler cmdHandler3 = null;

  @Before
  public void setUp() throws Exception {
    String[] args1 = {
        "--simulation", "1",
        "--mode", "1",
        "--max-pause", "1",
        "--max-treatment", "1",
        "--number-rooms", "5"
    };

    String[] args2 = {
        "2", "1",
        "--mode", "a",
        "--max-pause", "1",
        "--max-treatment", "1",
        "--number-rooms", "5"
    };

    String[] args3 = {
        "--simulation-time", "1",
        "--mode", "1"
    };

    cmdHandler1 = new CMDHandler(args1);
    cmdHandler2 = new CMDHandler(args2);
    cmdHandler3 = new CMDHandler(args3);
  }

  @Test
  public void isLegalFormat() throws Exception {
    assertTrue(!cmdHandler1.isLegalFormat());
    assertTrue(!cmdHandler2.isLegalFormat());
    assertTrue(!cmdHandler3.isLegalFormat());
  }

  @Test
  public void getErrorMessage() throws Exception {
    assertTrue(cmdHandler1.getErrorMessage() != null);
    assertTrue(cmdHandler2.getErrorMessage() != null);
    assertTrue(cmdHandler3.getErrorMessage() != null);
  }

  @Test
  public void getMode() throws Exception {
    assertTrue(cmdHandler1.getMode() == -1);
    assertTrue(cmdHandler2.getMode() == -1);
    assertTrue(cmdHandler3.getMode() == -1);
  }

  @Test
  public void getNumRooms() throws Exception {
    assertTrue(cmdHandler1.getNumRooms() == -1);
    assertTrue(cmdHandler2.getNumRooms() == -1);
    assertTrue(cmdHandler3.getNumRooms() == -1);
  }

  @Test
  public void getSimulationTime() throws Exception {
    assertTrue(cmdHandler1.getSimulationTime() == null);
    assertTrue(cmdHandler2.getSimulationTime() == null);
    assertTrue(cmdHandler3.getSimulationTime() == null);
  }

  @Test
  public void getMaxTreatment() throws Exception {
    assertTrue(cmdHandler1.getMaxTreatment() == null);
    assertTrue(cmdHandler2.getMaxTreatment() == null);
    assertTrue(cmdHandler3.getMaxTreatment() == null);
  }

  @Test
  public void getMaxPause() throws Exception {
    assertTrue(cmdHandler1.getMaxPause() == null);
    assertTrue(cmdHandler2.getMaxPause() == null);
    assertTrue(cmdHandler3.getMaxPause() == null);
  }

}