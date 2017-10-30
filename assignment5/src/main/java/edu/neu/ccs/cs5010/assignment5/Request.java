package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;

/**
 * The Request class represents the client request sent to the bank. A request contains
 * the ID of client who sent this request, and the message signature pair.
 *
 * @author Shuwan Huang
 */
public class Request implements IRequest {

  private final int clientId;
  private final BigInteger message;
  private final BigInteger signature;

  /**
   * Constructs a new Request with the client ID, message and signature.
   * @param clientId the client ID
   * @param message the message
   * @param signature the signature
   */
  public Request(int clientId, BigInteger message, BigInteger signature) {
    if (message == null || signature == null) {
      throw new IllegalArgumentException("message or signature is null");
    }
    this.clientId = clientId;
    this.message = message;
    this.signature = signature;
  }

  /**
   * Returns the ID of client who sent this request.
   * @return the ID of client who sent this request.
   */
  @Override
  public int getClientId() {
    return clientId;
  }

  /**
   * Returns the message contained in this request.
   * @return the message contained in this request.
   */
  @Override
  public BigInteger getMessage() {
    return message;
  }

  /**
   * Returns the signature of this request.
   * @return the signature of this request.
   */
  @Override
  public BigInteger getSignature() {
    return signature;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Request request = (Request) other;

    if (clientId != request.clientId) {
      return false;
    }

    if (!message.equals(request.message)) {
      return false;
    }
    return signature.equals(request.signature);
  }

  @Override
  public int hashCode() {
    int result = clientId;
    result = 31 * result + message.hashCode();
    result = 31 * result + signature.hashCode();
    return result;
  }
}
