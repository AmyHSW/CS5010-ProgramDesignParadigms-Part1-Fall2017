package edu.neu.ccs.cs5010.assignment5;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * The Bank class represents a bank that handles requests from clients. The bank can add new
 * clients to build the clients info system. When receives a request from client, the bank
 * verifies the signature using the client's public key. If the signature is verified, the bank
 * will identify the transaction status by checking the client's deposit / withdrawal limits.
 *
 * @author Shuwan Huang
 */
public class Bank implements IBank {

  private static final int DEPOSIT_REQUEST = 4;

  private final Map<Integer, IClientInfo> clientInfoMap = new HashMap<>();
  private final Map<IRequest, String> requestStatusMap = new HashMap<>();

  private final Set<IClientInfo> clientsSentRequest = new HashSet<>();
  private final Set<Integer> idDepositRejected = new HashSet<>();
  private final Set<Integer> idWithdrawalRejected = new HashSet<>();
  private final Map<IKey, Integer> publicKeyUsage = new HashMap<>();

  /**
   * Adds a new client to this bank.
   * @param clientId the client ID
   * @param publicKey the public key of the client
   */
  @Override
  public void addClient(int clientId, IKey publicKey) {
    IClientInfo clientInfo = new ClientInfo(clientId, publicKey);
    clientInfoMap.put(clientId, clientInfo);
  }

  /**
   * Receives a client request. Returns true if the request is verified.
   * @param request a request
   * @return true if the request is verified, false otherwise.
   */
  @Override
  public boolean receiveRequest(IRequest request) {
    int clientId = request.getClientId();
    IClientInfo clientInfo = clientInfoMap.get(clientId);
    clientInfo.addTransaction();
    clientsSentRequest.add(clientInfo);

    IKey publicKey = clientInfo.getPublicKey();
    if (publicKeyUsage.containsKey(publicKey)) {
      publicKeyUsage.put(publicKey, publicKeyUsage.get(publicKey) + 1);
    } else {
      publicKeyUsage.put(publicKey, 1);
    }

    BigInteger messageDecrypted = publicKey.encryptOrDecrypt(request.getSignature());
    boolean isVerified = messageDecrypted.equals(request.getMessage());
    if (!isVerified) {
      requestStatusMap.put(request, "NA");
      return false;
    } else {
      String status = identifyTransaction(request.getMessage(), clientId);
      requestStatusMap.put(request, status);
      return true;
    }
  }

  // identifies the status of transaction requested by client
  private String identifyTransaction(BigInteger message, int clientId) {
    IClientInfo clientInfo = clientInfoMap.get(clientId);
    int lastDigit = message.intValue() % 10;
    int amount = message.intValue() / 10;
    StringBuilder status = new StringBuilder();
    if (isDepositRequest(lastDigit)) {
      status.append("deposit ");
      if (amount <= clientInfo.getDepositLimit()) {
        status.append("accepted");
      } else {
        status.append("declined");
        idDepositRejected.add(clientId);
      }
    } else {
      status.append("withdrawal ");
      if (amount <= clientInfo.getWithdrawalLimit()) {
        status.append("accepted");
      } else {
        status.append("declined");
        idWithdrawalRejected.add(clientId);
      }
    }
    return status.toString();
  }

  private boolean isDepositRequest(int digit) {
    return digit <= DEPOSIT_REQUEST;
  }

  /**
   * Returns the transaction status of the client request.
   * @param request a client request
   * @return the transaction status
   */
  @Override
  public String getRequestStatus(IRequest request) {
    return requestStatusMap.get(request);
  }

  /**
   * Returns a set of IDs that hava rejected deposit history.
   * @return a set of IDs that hava rejected deposit history.
   */
  @Override
  public Set<Integer> getIdDepositRejected() {
    return idDepositRejected;
  }

  /**
   * Returns a set of IDs that hava rejected withdrawal history.
   * @return a set of IDs that hava rejected withdrawal history.
   */
  @Override
  public Set<Integer> getIdWithdrawalRejected() {
    return idWithdrawalRejected;
  }

  /**
   * Returns the usage of public keys.
   * @return the usage of public keys.
   */
  @Override
  public Map<IKey, Integer> getPublicKeyUsage() {
    return publicKeyUsage;
  }

  /**
   * Returns a queue of clients who have sent requests to this bank ordered by
   * number of transactions.
   * @return a queue of clients who have sent requests to this bank ordered by
   *         number of transactions.
   */
  @Override
  public Queue<IClientInfo> getClientsSentRequest() {
    return new PriorityQueue<>(clientsSentRequest);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Bank bank = (Bank) other;

    if (!clientInfoMap.equals(bank.clientInfoMap)) {
      return false;
    }
    return requestStatusMap.equals(bank.requestStatusMap);
  }

  @Override
  public int hashCode() {
    int result = clientInfoMap.hashCode();
    result = 31 * result + requestStatusMap.hashCode();
    return result;
  }
}
