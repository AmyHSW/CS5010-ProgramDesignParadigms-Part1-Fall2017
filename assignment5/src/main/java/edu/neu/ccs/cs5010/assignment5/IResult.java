package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

public interface IResult {

  int getClientID();

  int getTransactionNum();

  LocalTime getTime();

  LocalDate getDate();

  String getStatus();

  BigInteger getMessage();

  BigInteger getDigitalSignature();

  String getVerified();

}
