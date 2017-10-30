package edu.neu.ccs.cs5010.assignment5;

import org.junit.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ResultTest {

  @Test
  public void testEquals() throws Exception {
    LocalDateTime now = LocalDateTime.now();
    IRequest request = new Request(1, BigInteger.ONE, BigInteger.TEN);
    IResult result = new Result(1, now, request, true, "NA");
    assertTrue(result.equals(result));
    assertTrue(!result.equals(null));
    assertTrue(!result.equals(""));
    assertTrue(!result.equals(new Result(2, now, request, true, "NA")));
    assertTrue(!result.equals(new Result(1, now.plusMinutes(1), request, true, "NA")));
    IRequest request2 = new Request(2, BigInteger.ZERO, BigInteger.TEN);
    assertTrue(!result.equals(new Result(1, now, request2, true, "NA")));
    assertTrue(!result.equals(new Result(1, now, request, true, "status")));
    assertTrue(result.equals(new Result(1, now, request, true, "NA")));
  }

  @Test
  public void testHashCode() throws Exception {
    LocalDateTime now = LocalDateTime.now();
    IRequest request = new Request(1, BigInteger.ONE, BigInteger.TEN);
    IResult result = new Result(1, now, request, true, "NA");
    IResult result2 = new Result(1, now, request, true, "NA");
    assertTrue(result.hashCode() == result2.hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException1() throws Exception {
    IRequest request = new Request(1, BigInteger.ONE, BigInteger.TEN);
    new Result(1, null, request, true, "NA");
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException2() throws Exception {
    new Result(1, LocalDateTime.now(), null, true, "NA");
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException3() throws Exception {
    IRequest request = new Request(1, BigInteger.ONE, BigInteger.TEN);
    new Result(1, LocalDateTime.now(), request, true, null);
  }
}