package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;

/**
 * The IClient interface represents a client.
 *
 * @author Shuwan Huang
 */
public interface IClient {

  /**
   * Returns the client ID.
   * @return the client ID.
   */
  int getId();

  /**
   * Returns the public key of this client.
   * @return the public key of this client.
   */
  IKey getPublicKey();

  /**
   * Provides a signature for the message using the private key. The underlying algorithm is
   * RSA signature generation algorithm.
   * @param message the message to be encrypted.
   * @return the signature.
   */
  BigInteger provideSignature(BigInteger message);

}
