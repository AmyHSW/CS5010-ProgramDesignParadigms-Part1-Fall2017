package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;

public class Key implements IKey {

  private final BigInteger number;
  private final BigInteger modulus;

  public Key(BigInteger number, BigInteger modulus) {
    if (number == null || modulus == null) {
      throw new IllegalArgumentException("number or modulus is null.");
    }
    this.number = number;
    this.modulus = modulus;
  }

  @Override
  public BigInteger translate(BigInteger signature) {
    return signature.modPow(number, modulus);
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
