package edu.neu.ccs.cs5010.assignment5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * The CsvFileGenerator class generates a csv file for the results of simulation.
 *
 * @author Shuwan Huang
 */
public class CsvFileGenerator implements ICsvFileGenerator {

  private static final String HEADERS = "Transaction number, Date, Time, Client ID, "
                                      + "Message, Digital signature, "
                                      + "Verified, Transaction status\n";

  /**
   * Generates a csv file with the provided file name for the results of simulation.
   * @param results the results of simulation
   * @param outputDir the csv file name
   * @throws IOException if there exists an I/O failure
   */
  @Override
  public void generateCsvFile(List<IResult> results, String outputDir) throws IOException {
    StringBuilder stringBuilder = new StringBuilder(HEADERS);
    for (IResult result : results) {
      stringBuilder.append(result.getTransactionNum())
          .append(", ")
          .append(result.getDate())
          .append(", ")
          .append(result.getTime())
          .append(", ")
          .append(result.getClientId())
          .append(", ")
          .append(result.getMessage())
          .append(", ")
          .append(result.getDigitalSignature())
          .append(", ")
          .append(result.getVerified())
          .append(", ")
          .append(result.getTransactionStatus())
          .append("\n");
    }
    writeStringToCsvFile(stringBuilder.toString(), outputDir);
  }

  private void writeStringToCsvFile(String str, String outputDir) throws IOException {
    File file = new File(outputDir);
    OutputStream out = new FileOutputStream(file);
    Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
    BufferedWriter bufferedWriter = new BufferedWriter(writer);
    try {
      bufferedWriter.write(str);
    } finally {
      bufferedWriter.flush();
      bufferedWriter.close();
    }
  }
}
