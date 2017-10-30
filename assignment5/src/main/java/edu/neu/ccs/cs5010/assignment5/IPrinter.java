package edu.neu.ccs.cs5010.assignment5;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * The IPrinter interface represents a printer.
 *
 * @author Shuwan Huang
 */
public interface IPrinter {

  /**
   * Prints to console all IDs with rejected deposit transactions.
   * @param idSet a set of IDs with rejected deposit transactions.
   */
  void printIDsDepositRejected(Set<Integer> idSet);

  /**
   * Prints to console all IDs with rejected withdrawal transactions.
   * @param idSet a set of IDs with rejected withdrawal transactions.
   */
  void printIDsWithdrawalRejected(Set<Integer> idSet);

  /**
   * Prints to console the usage of public keys.
   * @param map a map in which the entry pair is public key and the number of transactions using
   *            the public key
   */
  void printPublicKeyUsage(Map<IKey, Integer> map);

  /**
   * Prints to console the top ten clients with the largest number of transactions.
   * @param queue a queue of client info ordered by number of transactions.
   */
  void printTopTenClients(Queue<IClientInfo> queue);

}
