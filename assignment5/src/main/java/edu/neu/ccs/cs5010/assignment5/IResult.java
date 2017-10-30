package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The IResult interface represents a result of verification.
 *
 * @author Shuwan Huang
 */
public interface IResult {

  /**
   * Returns the ID of client.
   * @return the ID of client.
   */
  int getClientId();

  /**
   * Returns the transaction number.
   * @return the transaction number.
   */
  int getTransactionNum();

  /**
   * Returns the time when the transaction is made.
   * @return the time when the transaction is made.
   */
  LocalTime getTime();

  /**
   * Returns the date when the transaction is made.
   * @return the date when the transaction is made.
   */
  LocalDate getDate();

  /**
   * Returns the transaction status.
   * @return the transaction status.
   */
  String getTransactionStatus();

  /**
   * Returns the message of the transaction.
   * @return the message of the transaction.
   */
  BigInteger getMessage();

  /**
   * Returns the digital signature of the transaction.
   * @return the digital signature of the transaction.
   */
  BigInteger getDigitalSignature();

  /**
   * Returns the verified status of the transaction.
   * @return the verified status of the transaction.
   */
  String getVerified();

}
