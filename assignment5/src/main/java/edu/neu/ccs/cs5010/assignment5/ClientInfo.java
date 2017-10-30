package edu.neu.ccs.cs5010.assignment5;

import java.util.Random;

/**
 * The ClientInfo class store the information of a client in the bank system. When
 * a new ClientInfo is constructed, a deposit limit and a withdrawal limit will be set
 * randomly for this client. Besides the deposit/withdrawal limits, the ClientInfo also
 * stores the client ID, the public key, and the number of transactions that a client has
 * requested.
 *
 * @author Shuwan Huang
 */
public class ClientInfo implements IClientInfo {

  private static final int DEPOSIT_LIMIT_UPPER_BOUND = 2000;
  private static final int WITHDRAWAL_LIMIT_UPPER_BOUND = 3000;

  private final int clientId;
  private final IKey publicKey;
  private final int depositLimit;
  private final int withdrawalLimit;
  private int numTransactions = 0;

  /**
   * Constructs a new ClientInfo with the client ID and the public key of a client.
   * @param clientId the client ID
   * @param publicKey the public key
   */
  public ClientInfo(int clientId, IKey publicKey) {
    if (publicKey == null) {
      throw new IllegalArgumentException("public key is null");
    }
    this.clientId = clientId;
    this.publicKey = publicKey;
    depositLimit = initDepositLimit();
    withdrawalLimit = initWithdrawalLimit();
  }

  private int initDepositLimit() {
    Random random = new Random();
    return random.nextInt(DEPOSIT_LIMIT_UPPER_BOUND + 1);
  }

  private int initWithdrawalLimit() {
    Random random = new Random();
    return random.nextInt(WITHDRAWAL_LIMIT_UPPER_BOUND + 1);
  }

  /**
   * Returns the client ID.
   * @return the client ID.
   */
  @Override
  public int getClientId() {
    return clientId;
  }

  /**
   * Returns the public key.
   * @return the public key.
   */
  @Override
  public IKey getPublicKey() {
    return publicKey;
  }

  /**
   * Returns the deposit limit.
   * @return the deposit limit.
   */
  @Override
  public int getDepositLimit() {
    return depositLimit;
  }

  /**
   * Returns the withdrawal limit.
   * @return the withdrawal limit.
   */
  @Override
  public int getWithdrawalLimit() {
    return withdrawalLimit;
  }

  /**
   * Increments the number of transactions in this client info by 1.
   */
  @Override
  public void addTransaction() {
    numTransactions++;
  }

  /**
   * Returns the number of transactions requested.
   * @return the number of transactions requested.
   */
  @Override
  public int getNumTransactions() {
    return numTransactions;
  }

  /**
   * Compares this client info with another client info. The one with more transactions has
   * higher priority.
   * @param that the other client info
   * @return a positive integer if this client info has less transactions, a negative integer
   *         if this client info has more transactions, the number zero if the two client infos
   *         have equal number of transactions
   */
  @Override
  public int compareTo(IClientInfo that) {
    return that.getNumTransactions() - this.getNumTransactions();
  }

  @Override
  public String toString() {
    return "ClientInfo {clientId = " + clientId
         + ", numTransactions = " + numTransactions + " }";
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    ClientInfo that = (ClientInfo) other;

    if (clientId != that.clientId) {
      return false;
    }

    return publicKey.equals(that.publicKey);
  }

  @Override
  public int hashCode() {
    int result = clientId;
    result = 31 * result + publicKey.hashCode();
    return result;
  }
}
