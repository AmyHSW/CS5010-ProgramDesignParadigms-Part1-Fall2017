package edu.neu.ccs.cs5010.assignment5;

import org.junit.Test;

import static org.junit.Assert.*;

public class RsaKeyTest {

  @Test
  public void testEquals() throws Exception {
    IRsaKey rsaKey = new RsaKey();
    assertTrue(rsaKey.equals(rsaKey));
    assertTrue(!rsaKey.equals(null));
    assertTrue(!rsaKey.equals(""));
    assertTrue(!rsaKey.equals(new RsaKey()));
  }

  @Test
  public void testHashCode() throws Exception {
    IRsaKey rsaKey = new RsaKey();
    IRsaKey rsaKey1 = new RsaKey();
    assertTrue(rsaKey.hashCode() != rsaKey1.hashCode());
  }

}