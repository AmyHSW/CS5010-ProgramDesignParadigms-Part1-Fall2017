package edu.neu.ccs.cs5010.assignment5;

/**
 * The IRsaKey interface represents a RSA key.
 *
 * @author Shuwan Huang
 */
public interface IRsaKey {

  /**
   * Returns the private key.
   * @return the private key.
   */
  IKey getPrivateKey();

  /**
   * Returns the public key.
   * @return the public key.
   */
  IKey getPublicKey();

}
