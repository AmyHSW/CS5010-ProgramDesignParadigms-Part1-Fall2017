package edu.neu.ccs.cs5010.assignment5;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * The Printer class prints information about the simulation to console.
 *
 * @author Shuwan Huang
 */
public class Printer implements IPrinter {

  private static final int NUM_TOP_CLIENTS = 10;

  /**
   * Prints to console all IDs with rejected deposit transactions.
   * @param idSet a set of IDs with rejected deposit transactions.
   */
  @Override
  public void printIDsDepositRejected(Set<Integer> idSet) {
    System.out.println("** The following IDs have rejected deposit transactions: ");
    for (int clientId : idSet) {
      System.out.println("Client ID " + clientId);
    }
  }

  /**
   * Prints to console all IDs with rejected withdrawal transactions.
   * @param idSet a set of IDs with rejected withdrawal transactions.
   */
  @Override
  public void printIDsWithdrawalRejected(Set<Integer> idSet) {
    System.out.println("** The following IDs have rejected withdrawal transactions: ");
    for (int clientId : idSet) {
      System.out.println("Client ID " + clientId);
    }
  }

  /**
   * Prints to console the usage of public keys.
   * @param map a map in which the entry pair is public key and the number of transactions using
   *            the public key
   */
  @Override
  public void printPublicKeyUsage(Map<IKey, Integer> map) {
    System.out.println("** The following public keys were used for distinct transactions: ");
    for (Map.Entry<IKey, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 1) {
        continue;
      }
      System.out.println(entry.getKey() + " was used for " + entry.getValue() + " transactions");
    }
  }

  /**
   * Prints to console the top ten clients with the largest number of transactions.
   * @param queue a queue of client info ordered by number of transactions.
   */
  @Override
  public void printTopTenClients(Queue<IClientInfo> queue) {
    System.out.println("** The following 10 clients have the largest number of transactions：");
    for (int i = 0; i < NUM_TOP_CLIENTS; i++) {
      IClientInfo clientInfo = queue.poll();
      System.out.println(clientInfo);
      if (queue.isEmpty()) {
        break;
      }
    }
  }

}
