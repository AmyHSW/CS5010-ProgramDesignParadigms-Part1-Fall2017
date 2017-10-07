package edu.neu.ccs.cs5010.assignment3;

import java.util.Map;

public interface IArgumentsParser {
    boolean isLegalFormat();
    String getErrorMessage();
    String getEmailTemplate();
    String getOutputDir();
    String getCsvFile();
    Map<String, String> getFlightInfo();
}
