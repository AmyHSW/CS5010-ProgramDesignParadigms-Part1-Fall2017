package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;

/**
 * The IKey interface represents a key.
 *
 * @author Shuwan Huang
 */
public interface IKey {

  /**
   * Encrypts / decrypts a message using this key.
   * @param message the message to be encrypted/decrypted
   * @return a message translated from the given message
   */
  BigInteger encryptOrDecrypt(BigInteger message);

}
