package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The Result class contains the result of a verification.
 *
 * @author Shuwan Huang
 */
public class Result implements IResult {

  private final int transactionNum;
  private final LocalDateTime time;
  private final IRequest request;
  private final boolean isVerified;
  private final String status;

  /**
   * Constructs a new Result.
   * @param transactionNum the transaction number
   * @param time the time of this transaction
   * @param request the client request sent to the bank
   * @param isVerified a boolean whether the transction is verified
   * @param status the transaction status
   */
  public Result(int transactionNum, LocalDateTime time, IRequest request,
                boolean isVerified, String status) {
    if (time == null || request == null || status == null) {
      throw new IllegalArgumentException("Some argument(s) is null");
    }
    this.transactionNum = transactionNum;
    this.time = time;
    this.request = request;
    this.isVerified = isVerified;
    this.status = status;
  }

  /**
   * Returns the transaction number.
   * @return the transaction number.
   */
  @Override
  public int getTransactionNum() {
    return transactionNum;
  }

  /**
   * Returns the ID of client.
   * @return the ID of client.
   */
  @Override
  public int getClientId() {
    return request.getClientId();
  }

  /**
   * Returns the date when the transaction is made.
   * @return the date when the transaction is made.
   */
  @Override
  public LocalDate getDate() {
    return time.toLocalDate();
  }

  /**
   * Returns the time when the transaction is made.
   * @return the time when the transaction is made.
   */
  @Override
  public LocalTime getTime() {
    return time.toLocalTime();
  }

  /**
   * Returns the message of the transaction.
   * @return the message of the transaction.
   */
  @Override
  public BigInteger getMessage() {
    return request.getMessage();
  }

  /**
   * Returns the digital signature of the transaction.
   * @return the digital signature of the transaction.
   */
  @Override
  public BigInteger getDigitalSignature() {
    return request.getSignature();
  }

  /**
   * Returns the verified status of the transaction.
   * @return the verified status of the transaction.
   */
  @Override
  public String getVerified() {
    return isVerified ? "Yes" : "No";
  }

  /**
   * Returns the transaction status.
   * @return the transaction status.
   */
  @Override
  public String getTransactionStatus() {
    return status;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Result result = (Result) other;

    if (transactionNum != result.transactionNum) {
      return false;
    }
    if (!time.equals(result.time)) {
      return false;
    }
    if (!request.equals(result.request)) {
      return false;
    }
    if (isVerified != result.isVerified) {
      return false;
    }
    return status.equals(result.status);
  }

  @Override
  public int hashCode() {
    int result = transactionNum;
    result = 31 * result + time.hashCode();
    result = 31 * result + request.hashCode();
    result = 31 * result + (isVerified ? 1 : 0);
    result = 31 * result + status.hashCode();
    return result;
  }
}
