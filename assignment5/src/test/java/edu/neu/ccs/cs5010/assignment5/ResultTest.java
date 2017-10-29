package edu.neu.ccs.cs5010.assignment5;

import org.junit.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ResultTest {

  @Test
  public void testEquals() throws Exception {
    LocalDateTime now = LocalDateTime.now();
    IRequest request = new Request(BigInteger.ONE, BigInteger.TEN);
    IResult result = new Result(1, now, 1, request, "NA");
    assertTrue(result.equals(result));
    assertTrue(!result.equals(null));
    assertTrue(!result.equals(""));
    assertTrue(!result.equals(new Result(2, now, 1, request, "NA")));
    assertTrue(!result.equals(new Result(1, now, 2, request, "NA")));
    assertTrue(!result.equals(new Result(1, now.plusMinutes(1), 1, request, "NA")));
    IRequest request2 = new Request(BigInteger.ZERO, BigInteger.TEN);
    assertTrue(!result.equals(new Result(1, now, 1, request2, "NA")));
    assertTrue(!result.equals(new Result(1, now, 1, request, "status")));
    assertTrue(result.equals(new Result(1, now, 1, request, "NA")));
  }

  @Test
  public void testHashCode() throws Exception {
    LocalDateTime now = LocalDateTime.now();
    IRequest request = new Request(BigInteger.ONE, BigInteger.TEN);
    IResult result = new Result(1, now, 1, request, "NA");
    IResult result2 = new Result(1, now, 1, request, "NA");
    assertTrue(result.hashCode() == result2.hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException1() throws Exception {
    IRequest request = new Request(BigInteger.ONE, BigInteger.TEN);
    new Result(1, null, 1, request, "NA");
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException2() throws Exception {
    new Result(1, LocalDateTime.now(), 1, null, "NA");
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException3() throws Exception {
    IRequest request = new Request(BigInteger.ONE, BigInteger.TEN);
    new Result(1, LocalDateTime.now(), 1, request, null);
  }
}