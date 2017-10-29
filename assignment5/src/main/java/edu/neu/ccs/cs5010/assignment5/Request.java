package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;

public class Request implements IRequest {

  private final BigInteger message;
  private final BigInteger signature;

  public Request(BigInteger message, BigInteger signature) {
    if (message == null || signature == null) {
      throw new IllegalArgumentException("message or signature is null");
    }
    this.message = message;
    this.signature = signature;
  }

  @Override
  public BigInteger getMessage() {
    return message;
  }

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

    if (!message.equals(request.message)) {
      return false;
    }
    return signature.equals(request.signature);
  }

  @Override
  public int hashCode() {
    int result = message.hashCode();
    result = 31 * result + signature.hashCode();
    return result;
  }
}
