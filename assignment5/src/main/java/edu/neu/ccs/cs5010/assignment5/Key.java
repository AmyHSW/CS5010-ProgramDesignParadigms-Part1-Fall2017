package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;

/**
 * The Key class represents a public or private key in a RSA key pair. Given a message,
 * the key can encrypt or decrypt it to another message.
 *
 * @author Shuwan Huang
 */
public class Key implements IKey {

  private final BigInteger number;
  private final BigInteger modulus;

  /**
   * Constructs a Key with a number and a modulus.
   * @param number a BigInteger
   * @param modulus a BigInteger
   */
  public Key(BigInteger number, BigInteger modulus) {
    if (number == null || modulus == null) {
      throw new IllegalArgumentException("number or modulus is null.");
    }
    this.number = number;
    this.modulus = modulus;
  }

  /**
   * Encrypts/decrypts a message using this key.
   * @param message the message to be encrypted/decrypted
   * @return a message translated from the given message
   */
  @Override
  public BigInteger encryptOrDecrypt(BigInteger message) {
    return message.modPow(number, modulus);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Key publicKey = (Key) other;

    if (!number.equals(publicKey.number)) {
      return false;
    }
    return modulus.equals(publicKey.modulus);
  }

  @Override
  public int hashCode() {
    int result = number.hashCode();
    result = 31 * result + modulus.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "(" + number + ", " + modulus + ")";
  }

}
