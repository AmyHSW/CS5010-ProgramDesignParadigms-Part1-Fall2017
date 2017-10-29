package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RsaKey implements IRsaKey {

  private static final SecureRandom RANDOM = new SecureRandom();
  private static final int BIT_LENGTH = 32;

  private IKey publicKey;
  private IKey privateKey;

  public RsaKey() {
    createKeys();
  }

  private void createKeys() {
    BigInteger primeQ = BigInteger.probablePrime(BIT_LENGTH, RANDOM);
    BigInteger primeP = BigInteger.probablePrime(BIT_LENGTH, RANDOM);
    BigInteger phi = (primeP.subtract(BigInteger.ONE)).multiply(primeQ.subtract(BigInteger.ONE));

    BigInteger modulus = primeP.multiply(primeQ);
    BigInteger random = BigInteger.probablePrime(BIT_LENGTH / 2, RANDOM);
    while (!random.gcd(phi).equals(BigInteger.ONE)
            || !random.gcd(modulus).equals(BigInteger.ONE)) {
      random = random.add(BigInteger.ONE);
    }
    publicKey = new Key(random, modulus);
    privateKey = new Key(random.modInverse(phi), modulus);
  }

  @Override
  public IKey getPrivateKey() {
    return privateKey;
  }

  @Override
  public IKey getPublicKey() {
    return publicKey;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    RsaKey rsaKey = (RsaKey) other;

    if (!publicKey.equals(rsaKey.publicKey)) {
      return false;
    }
    return privateKey.equals(rsaKey.privateKey);
  }

  @Override
  public int hashCode() {
    int result = publicKey.hashCode();
    result = 31 * result + privateKey.hashCode();
    return result;
  }
}
