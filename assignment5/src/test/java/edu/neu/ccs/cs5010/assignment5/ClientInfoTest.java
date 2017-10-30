package edu.neu.ccs.cs5010.assignment5;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class ClientInfoTest {

  private IClientInfo clientInfo;

  @Before
  public void setUp() throws Exception {
    clientInfo = new ClientInfo(1, new Key(BigInteger.ONE, BigInteger.TEN));
  }

  @Test
  public void getClientId() throws Exception {
    assertTrue(clientInfo.getClientId() == 1);
  }

  @Test
  public void compareTo() throws Exception {
    IClientInfo clientInfo2 = new ClientInfo(2, new Key(BigInteger.ONE, BigInteger.TEN));
    clientInfo2.addTransaction();
    assertTrue(clientInfo2.compareTo(clientInfo) < 0);
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!clientInfo.equals(null));
    assertTrue(!clientInfo.equals(""));
    assertTrue(clientInfo.equals(clientInfo));
    assertTrue(clientInfo.equals(new ClientInfo(1, new Key(BigInteger.ONE, BigInteger.TEN))));
    assertTrue(!clientInfo.equals(new ClientInfo(2, new Key(BigInteger.ONE, BigInteger.TEN))));
    assertTrue(!clientInfo.equals(new ClientInfo(1, new Key(BigInteger.ONE, BigInteger.ZERO))));
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException() throws Exception {
    new ClientInfo(1, null);
  }

}