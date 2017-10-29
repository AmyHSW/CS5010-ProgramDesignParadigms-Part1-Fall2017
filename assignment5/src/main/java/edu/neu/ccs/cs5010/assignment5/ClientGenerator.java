package edu.neu.ccs.cs5010.assignment5;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ClientGenerator implements IClientGenerator {

  private static final int DEPOSIT_LIMIT_UPPER_BOUND = 2000;
  private static final int WITHDRAWAL_LIMIT_UPPER_BOUND = 3000;

  private final Set<Integer> idSet;

  public ClientGenerator() {
    idSet = new HashSet<>();
  }

  @Override
  public IClient nextClient() {
    Random random = new Random();
    int clientId = random.nextInt(Integer.MAX_VALUE);
    while (idSet.contains(clientId)) {
      clientId++;
    }
    idSet.add(clientId);
    int depositLimit = random.nextInt(DEPOSIT_LIMIT_UPPER_BOUND + 1);
    int withdrawalLimit = random.nextInt(WITHDRAWAL_LIMIT_UPPER_BOUND + 1);
    return new Client(clientId, depositLimit, withdrawalLimit);
  }
}
