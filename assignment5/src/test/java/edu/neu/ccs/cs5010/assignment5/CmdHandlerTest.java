package edu.neu.ccs.cs5010.assignment5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CmdHandlerTest {

  private ICmdHandler handler;
  private String[] args;

  @Before
  public void setUp() throws Exception {
    args = new String[] {"10", "5", "1", "test.csv"};
    handler = new CmdHandler(args);
  }

  @Test
  public void isValid() throws Exception {
    String[] args0 = {"12", "2", "0"};
    assertFalse(new CmdHandler(args0).isValid());
    String[] args1 = {"a", "2", "0", "test.csv"};
    assertFalse(new CmdHandler(args1).isValid());
    String[] args2 = {"-1", "2", "0", "test.csv"};
    assertFalse(new CmdHandler(args2).isValid());
    String[] args3 = {"50000000", "2", "0", "test.csv"};
    assertFalse(new CmdHandler(args3).isValid());
    String[] args4 = {"12", "b", "0", "test.csv"};
    assertFalse(new CmdHandler(args4).isValid());
    String[] args5 = {"12", "-2", "0", "test.csv"};
    assertFalse(new CmdHandler(args5).isValid());
    String[] args6 = {"12", "20000", "0", "test.csv"};
    assertFalse(new CmdHandler(args6).isValid());
    String[] args7 = {"12", "2", "c", "test.csv"};
    assertFalse(new CmdHandler(args7).isValid());
    String[] args8 = {"12", "2", "2", "test.csv"};
    assertFalse(new CmdHandler(args8).isValid());
    String[] args9 = {"12", "2", "2", "test"};
    assertFalse(new CmdHandler(args9).isValid());
  }

  @Test
  public void testEquals() throws Exception {
    assertFalse(handler.equals(null));
    assertTrue(handler.equals(handler));
    assertTrue(handler.equals(new CmdHandler(args)));
    String[] args0 = {"12", "2", "0"};
    assertFalse(handler.equals(new CmdHandler(args0)));
    String[] args1 = {"4", "5", "1", "test.csv"};
    assertFalse(handler.equals(new CmdHandler(args1)));
    String[] args2 = {"10", "2", "1", "test.csv"};
    assertFalse(handler.equals(new CmdHandler(args2)));
    String[] args3 = {"10", "5", "0", "test.csv"};
    assertFalse(handler.equals(new CmdHandler(args3)));
    String[] args4 = {"10", "5", "1", "test1.csv"};
    assertFalse(handler.equals(new CmdHandler(args4)));
    String[] args5 = {"4", "5", "1", null};
    assertTrue((new CmdHandler(args5)).equals(new CmdHandler(args5)));
  }

  @Test
  public void testHashCode() throws Exception {
    String[] args9 = {"12", "2", "2", "test"};
    assertTrue(handler.hashCode() != (new CmdHandler(args9)).hashCode());
  }

}