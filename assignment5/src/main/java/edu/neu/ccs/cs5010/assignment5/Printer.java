package edu.neu.ccs.cs5010.assignment5;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Printer implements IPrinter {

  @Override
  public void printIDsDepositRejected(Set<Integer> idSet) {
    System.out.println("** The following IDs have rejected deposit transactions: ");
    for (int clientId : idSet) {
      System.out.println("Client ID " + clientId);
    }
  }

  @Override
  public void printIDsWithdrawalRejected(Set<Integer> idSet) {
    System.out.println("** The following IDs have rejected withdrawal transactions: ");
    for (int clientId : idSet) {
      System.out.println("Client ID " + clientId);
    }
  }

  @Override
  public void printPublicKeyUsage(Map<IKey, Integer> map) {
    System.out.println("** The following public keys were used for distinct transactions "
                       + "with different IDs: ");
    for (Map.Entry<IKey, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 1) {
        continue;
      }
      System.out.println(entry.getKey() + " was used for " + entry.getValue() + " transactions");
    }
  }

  @Override
  public void printTopClients(Queue<IClient> queue) {
    System.out.println("** The following 10 clients have the largest number of transactions：");
    for (int i = 0; i < 10; i++) {
      IClient client = queue.poll();
      System.out.println(client + " has " + client.getNumTransactions() + " transactions");
      if (queue.isEmpty()) {
        break;
      }
    }
  }

}
