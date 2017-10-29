package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Result implements IResult {

  private final int transactionNum;
  private final LocalDateTime time;
  private final int clientId;
  private final IRequest request;
  private final String status;

  public Result(int transactionNum, LocalDateTime time, int clientId,
                IRequest request, String status) {
    if (time == null || request == null || status == null) {
      throw new IllegalArgumentException("Some argument(s) is null");
    }
    this.transactionNum = transactionNum;
    this.time = time;
    this.clientId = clientId;
    this.request = request;
    this.status = status;
  }

  @Override
  public int getTransactionNum() {
    return transactionNum;
  }

  @Override
  public int getClientID() {
    return clientId;
  }

  @Override
  public LocalDate getDate() {
    return time.toLocalDate();
  }

  @Override
  public LocalTime getTime() {
    return time.toLocalTime();
  }

  @Override
  public BigInteger getMessage() {
    return request.getMessage();
  }

  @Override
  public BigInteger getDigitalSignature() {
    return request.getSignature();
  }

  @Override
  public String getVerified() {
    return status.equals("NA") ? "No" : "Yes";
  }

  @Override
  public String getStatus() {
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
    if (clientId != result.clientId) {
      return false;
    }
    if (!time.equals(result.time)) {
      return false;
    }
    if (!request.equals(result.request)) {
      return false;
    }
    return status.equals(result.status);
  }

  @Override
  public int hashCode() {
    int result = transactionNum;
    result = 31 * result + time.hashCode();
    result = 31 * result + clientId;
    result = 31 * result + request.hashCode();
    result = 31 * result + status.hashCode();
    return result;
  }

}
