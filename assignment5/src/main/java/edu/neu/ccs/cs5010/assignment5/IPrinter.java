package edu.neu.ccs.cs5010.assignment5;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

public interface IPrinter {

  void printIDsDepositRejected(Set<Integer> set);

  void printIDsWithdrawalRejected(Set<Integer> set);

  void printPublicKeyUsage(Map<IKey, Integer> map);

  void printTopClients(Queue<IClient> queue);

}
