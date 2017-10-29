package edu.neu.ccs.cs5010.assignment5;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class KeyTest {

  private IKey key;

  @Before
  public void setUp() throws Exception {
    key = new Key(new BigInteger("111"), new BigInteger("222"));
  }

  @Test
  public void testEquals() throws Exception {
    assertTrue(key.equals(key));
    assertTrue(!key.equals(null));
    assertTrue(!key.equals(""));
    assertTrue(!key.equals(new Key(BigInteger.ONE, new BigInteger("222"))));
    assertTrue(!key.equals(new Key(new BigInteger("111"), BigInteger.ONE)));
    assertTrue(key.equals(new Key(new BigInteger("111"), new BigInteger("222"))));
  }

  @Test
  public void testHashCode() throws Exception {
    IKey key2 = new Key(new BigInteger("111"), new BigInteger("222"));
    assertTrue(key.hashCode() == key2.hashCode());
  }

  @Test
  public void testToString() throws Exception {
    assertTrue(key.toString().equals("(111, 222)"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException1() throws Exception {
    new Key(null, new BigInteger("11"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void expectedIllegalArgumentException2() throws Exception {
    new Key(new BigInteger("11"), null);
  }
}