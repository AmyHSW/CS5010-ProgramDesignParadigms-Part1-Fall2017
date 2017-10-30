package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;

/**
 * The Client class represents a bank client. Each client object has a client ID, a RSA key
 * pair (public key and private key). Given a message, the client can provide a signature for
 * the message using the private key.
 *
 * @author Shuwan Huang
 */
public class Client implements IClient {

  private final int clientId;

  private IKey publicKey;
  private IKey privateKey;

  /**
   * Constructs a new Client object with a client ID.
   * @param clientId an integer
   */
  public Client(int clientId) {
    this.clientId = clientId;
    initKeys();
  }

  private void initKeys() {
    IRsaKey rsaKey = new RsaKey();
    publicKey = rsaKey.getPublicKey();
    privateKey = rsaKey.getPrivateKey();
  }

  /**
   * Returns the client ID.
   * @return the client ID.
   */
  @Override
  public int getId() {
    return clientId;
  }

  /**
   * Returns the public key of this client.
   * @return the public key of this client.
   */
  @Override
  public IKey getPublicKey() {
    return publicKey;
  }

  /**
   * Provides a signature for the message using the private key. The underlying algorithm is
   * RSA signature generation algorithm.
   * @param message the message to be encrypted.
   * @return the signature.
   */
  @Override
  public BigInteger provideSignature(BigInteger message) {
    return privateKey.encryptOrDecrypt(message);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Client client = (Client) other;

    return clientId == client.clientId;
  }

  @Override
  public int hashCode() {
    return clientId;
  }

  @Override
  public String toString() {
    return "Client ID " + clientId;
  }
}
