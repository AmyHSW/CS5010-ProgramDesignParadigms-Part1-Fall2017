package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;

/**
 * The IRequest interface represents a request sent to the bank from a client.
 *
 * @author Shuwan Huang
 */
public interface IRequest {

  /**
   * Returns the ID of client who sent this request.
   * @return the ID of client who sent this request.
   */
  int getClientId();

  /**
   * Returns the message contained in this request.
   * @return the message contained in this request.
   */
  BigInteger getMessage();

  /**
   * Returns the signature of this request.
   * @return the signature of this request.
   */
  BigInteger getSignature();

}
