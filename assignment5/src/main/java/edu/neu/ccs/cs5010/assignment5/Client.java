package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;

public class Client implements IClient {

  private final int clientId;
  private final int depositLimit;
  private final int withdrawalLimit;

  private IKey publicKey;
  private IKey privateKey;
  private int numTransactions = 0;

  public Client(int clientId, int depositLimit, int withdrawalLimit) {
    this.clientId = clientId;
    this.depositLimit = depositLimit;
    this.withdrawalLimit = withdrawalLimit;

    initKeys();
  }

  private void initKeys() {
    IRsaKey rsaKey = new RsaKey();
    publicKey = rsaKey.getPublicKey();
    privateKey = rsaKey.getPrivateKey();
  }

  @Override
  public void addTransaction() {
    numTransactions++;
  }

  @Override
  public int getNumTransactions() {
    return numTransactions;
  }

  @Override
  public int compareTo(IClient that) {
    return that.getNumTransactions() - this.getNumTransactions();
  }

  @Override
  public int getID() {
    return clientId;
  }

  @Override
  public int getDepositLimit() {
    return depositLimit;
  }

  @Override
  public int getWithdrawalLimit() {
    return withdrawalLimit;
  }

  @Override
  public IKey getPublicKey() {
    return publicKey;
  }

  @Override
  public BigInteger provideSignature(BigInteger message) {
    return privateKey.translate(message);
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

    if (clientId != client.clientId) {
      return false;
    }
    if (depositLimit != client.depositLimit) {
      return false;
    }
    if (withdrawalLimit != client.withdrawalLimit) {
      return false;
    }
    if (!publicKey.equals(client.publicKey)) {
      return false;
    }
    return privateKey.equals(client.privateKey);
  }

  @Override
  public int hashCode() {
    int result = clientId;
    result = 31 * result + depositLimit;
    result = 31 * result + withdrawalLimit;
    result = 31 * result + publicKey.hashCode();
    result = 31 * result + privateKey.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Client ID " + clientId;
  }
}
