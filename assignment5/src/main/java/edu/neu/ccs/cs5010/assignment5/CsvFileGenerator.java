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

public class CsvFileGenerator implements ICsvFileGenerator {

  private static final String HEADERS = "Transaction number, Date, Time, Client ID, "
                                      + "Message, Digital signature, "
                                      + "Verified, Transaction status\n";

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
          .append(result.getClientID())
          .append(", ")
          .append(result.getMessage())
          .append(", ")
          .append(result.getDigitalSignature())
          .append(", ")
          .append(result.getVerified())
          .append(", ")
          .append(result.getStatus())
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
