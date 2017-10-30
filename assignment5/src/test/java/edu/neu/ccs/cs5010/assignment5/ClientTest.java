package edu.neu.ccs.cs5010.assignment5;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class ClientTest {

  private IClient client;

  @Before
  public void setUp() throws Exception {
    client = new Client(1);
  }

  @Test
  public void provideSignature() throws Exception {
    BigInteger message = new BigInteger("1111");
    BigInteger signature = client.provideSignature(message);
    IKey key = client.getPublicKey();
    assertTrue(key.encryptOrDecrypt(signature).equals(message));
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!client.equals(null));
    assertTrue(!client.equals(""));
    assertTrue(client.equals(client));
    assertTrue(!client.equals(new Client(2)));
    assertTrue(client.equals(new Client(1)));
  }

  @Test
  public void testHashCode() throws Exception {
    IClient client2 = new Client(1);
    assertTrue(client.hashCode() == client2.hashCode());
  }
}