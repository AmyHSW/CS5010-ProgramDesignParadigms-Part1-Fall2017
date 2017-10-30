package edu.neu.ccs.cs5010.assignment5;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class BankTest {

  private IBank bank;
  private IClient client;

  @Before
  public void setUp() throws Exception {
    bank = new Bank();
    client = new Client(1);
    bank.addClient(1, client.getPublicKey());
  }

  @Test
  public void receiveRequest() throws Exception {
    BigInteger message = new BigInteger("352");
    BigInteger signature = client.provideSignature(message);
    IRequest request = new Request(1, message, signature);
    assertTrue(bank.receiveRequest(request));
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!bank.equals(null));
    assertTrue(!bank.equals(""));
    assertTrue(bank.equals(bank));
    assertTrue(!bank.equals(new Bank()));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(bank.hashCode() != (new Bank()).hashCode());
  }

}