package edu.neu.ccs.cs5010.assignment5;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * The IBank interface represents a bank.
 *
 * @author Shuwan Huang
 */
public interface IBank {

  /**
   * Adds a new client to this bank.
   * @param clientId the client ID
   * @param publicKey the public key of the client
   */
  void addClient(int clientId, IKey publicKey);

  /**
   * Receives a client request. Returns true if the request is verified.
   * @param request a request
   * @return true if the request is verified, false otherwise.
   */
  boolean receiveRequest(IRequest request);

  /**
   * Returns the transaction status of the client request.
   * @param request a client request
   * @return the transaction status
   */
  String getRequestStatus(IRequest request);

  /**
   * Returns a set of IDs that hava rejected deposit history.
   * @return a set of IDs that hava rejected deposit history.
   */
  Set<Integer> getIdDepositRejected();

  /**
   * Returns a set of IDs that hava rejected withdrawal history.
   * @return a set of IDs that hava rejected withdrawal history.
   */
  Set<Integer> getIdWithdrawalRejected();

  /**
   * Returns a queue of clients who have sent requests to this bank ordered by
   * number of transactions.
   * @return a queue of clients who have sent requests to this bank ordered by
   *         number of transactions.
   */
  Queue<IClientInfo> getClientsSentRequest();

  /**
   * Returns the usage of public keys.
   * @return the usage of public keys.
   */
  Map<IKey, Integer> getPublicKeyUsage();
}
