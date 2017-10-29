package edu.neu.ccs.cs5010.assignment5;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

public class SecureBankVerificationSimulator implements ISecureBankVerificationSimulator {

  private static final int MESSAGE_UPPER_BOUND = 30000;
  private static final int BIT_LENGTH = 32;
  private static final int DEPOSIT_REQUEST = 4;

  private final String outputDir;
  private final Map<Integer, IClient> clientsMap = new HashMap<>();
  private final Map<IClient, List<IRequest>> requestMap = new HashMap<>();
  private final List<IResult> results = new ArrayList<>();
  private final Set<Integer> idDepositRejected = new HashSet<>();
  private final Set<Integer> idWithdrawalRejected = new HashSet<>();
  private final Map<IKey, Integer> publicKeyUsage = new HashMap<>();

  public SecureBankVerificationSimulator(int numClients, int numVerifications,
                                         double fraction, String outputDir) {
    this.outputDir = outputDir;

    initClients(numClients);
    initRequests(numVerifications, fraction);
  }

  private void initClients(int numClients) {
    IClientGenerator generator = new ClientGenerator();
    for (int i = 0; i < numClients; i++) {
      IClient client = generator.nextClient();
      clientsMap.put(client.getID(), client);
    }
  }

  private void initRequests(int numVerifications, double fraction) {
    Integer[] idArr = new Integer[clientsMap.size()];
    idArr = clientsMap.keySet().toArray(idArr);

    Random random = new Random();
    for (int i = 0; i < numVerifications; i++) {
      int clientId = idArr[random.nextInt(idArr.length)];
      IClient client = clientsMap.get(clientId);
      client.addTransaction();

      int msgInt = random.nextInt(MESSAGE_UPPER_BOUND + 1);
      BigInteger message = new BigInteger(msgInt + "");

      IRequest request;
      BigInteger signature;
      if (isValidMessage(fraction)) {
        signature = clientsMap.get(clientId).provideSignature(message);
      } else {
        signature = new BigInteger(BIT_LENGTH, new SecureRandom());
      }

      request = new Request(message, signature);
      if (!requestMap.containsKey(client)) {
        requestMap.put(client, new ArrayList<>());
      }
      requestMap.get(client).add(request);
    }
  }

  private boolean isValidMessage(double fraction) {
    Random random = new Random();
    return random.nextDouble() >= fraction;
  }

  public void processVerifications() {
    int transactionNum = 0;

    for (Map.Entry<IClient, List<IRequest>> entry : requestMap.entrySet()) {
      IClient client = entry.getKey();
      IKey publicKey = client.getPublicKey();
      if (publicKeyUsage.containsKey(publicKey)) {
        publicKeyUsage.put(publicKey, publicKeyUsage.get(publicKey) + 1);
      } else {
        publicKeyUsage.put(publicKey, 1);
      }

      for (IRequest request : entry.getValue()) {
        String status = "NA";
        if (verify(request, publicKey)) {
          status = identifyTransaction(request.getMessage(), client);
        }
        IResult result = new Result(++transactionNum,
                                    LocalDateTime.now(),
                                    client.getID(),
                                    request,
                                    status);
        results.add(result);
      }
    }
  }

  private boolean verify(IRequest request, IKey key) {
    return key.translate(request.getSignature()).equals(request.getMessage());
  }

  private String identifyTransaction(BigInteger message, IClient client) {
    int lastDigit = message.intValue() % 10;
    int amount = message.intValue() / 10;
    StringBuilder status = new StringBuilder();
    if (isDepositRequest(lastDigit)) {
      status.append("deposit ");
      if (amount <= client.getDepositLimit()) {
        status.append("accepted");
      } else {
        idDepositRejected.add(client.getID());
        status.append("declined");
      }
    } else {
      status.append("withdrawal ");
      if (amount <= client.getWithdrawalLimit()) {
        status.append("accepted");
      } else {
        idWithdrawalRejected.add(client.getID());
        status.append("declined");
      }
    }
    return status.toString();
  }

  private boolean isDepositRequest(int digit) {
    return digit <= DEPOSIT_REQUEST;
  }

  @Override
  public void outputResults() throws IOException {
    IPrinter printer = new Printer();
    printer.printPublicKeyUsage(publicKeyUsage);
    printer.printTopClients(new PriorityQueue<>(requestMap.keySet()));
    printer.printIDsDepositRejected(idDepositRejected);
    printer.printIDsWithdrawalRejected(idWithdrawalRejected);

    ICsvFileGenerator csvFileGenerator = new CsvFileGenerator();
    csvFileGenerator.generateCsvFile(results, outputDir);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    SecureBankVerificationSimulator that = (SecureBankVerificationSimulator) other;

    if (outputDir != null ? !outputDir.equals(that.outputDir) : that.outputDir != null) {
      return false;
    }
    return clientsMap.equals(that.clientsMap) && requestMap.equals(that.requestMap);
  }

  @Override
  public int hashCode() {
    int result = outputDir != null ? outputDir.hashCode() : 0;
    result = 31 * result + clientsMap.hashCode();
    result = 31 * result + requestMap.hashCode();
    return result;
  }

  public static void main(String[] args) throws IOException {
    ICmdHandler cmdHandler = new CmdHandler(args);
    if (!cmdHandler.isValid()) {
      throw new InvalidInputException(cmdHandler.getErrorMessage());
    }
    ISecureBankVerificationSimulator simulator =
        new SecureBankVerificationSimulator(cmdHandler.getNumClients(),
                                            cmdHandler.getNumVerifications(),
                                            cmdHandler.getFraction(),
                                            cmdHandler.getOutputDir());
    simulator.processVerifications();
    simulator.outputResults();
  }

}
