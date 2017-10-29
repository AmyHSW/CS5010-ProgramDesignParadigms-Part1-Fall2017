package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;

public interface IClient extends Comparable<IClient> {

  int getID();

  int getDepositLimit();

  int getWithdrawalLimit();

  void addTransaction();

  int getNumTransactions();

  IKey getPublicKey();

  BigInteger provideSignature(BigInteger message);

}
