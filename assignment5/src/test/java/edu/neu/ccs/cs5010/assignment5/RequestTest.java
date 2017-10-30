package edu.neu.ccs.cs5010.assignment5;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class RequestTest {

  @Test
  public void testEquals() throws Exception {
    IRequest request = new Request(1, BigInteger.ZERO, BigInteger.ONE);
    assertTrue(request.equals(request));
    assertTrue(!request.equals(null));
    assertTrue(!request.equals(""));
    assertTrue(!request.equals(new Request(1, BigInteger.ONE, BigInteger.ONE)));
    assertTrue(!request.equals(new Request(1, BigInteger.ZERO, BigInteger.ZERO)));
    assertTrue(!request.equals(new Request(2, BigInteger.ZERO, BigInteger.ONE)));
    assertTrue(request.equals(new Request(1, BigInteger.ZERO, BigInteger.ONE)));
  }

  @Test
  public void testHashCode() throws Exception {
    IRequest request = new Request(1, BigInteger.ZERO, BigInteger.ONE);
    IRequest request2 = new Request(1, BigInteger.ZERO, BigInteger.ONE);
    assertTrue(request.hashCode() == request2.hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException1() throws Exception {
    new Request(1, null, BigInteger.ONE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException2() throws Exception {
    new Request(1, BigInteger.ONE, null);
  }
}