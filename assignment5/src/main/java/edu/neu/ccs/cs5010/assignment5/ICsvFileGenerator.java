package edu.neu.ccs.cs5010.assignment5;

import java.io.IOException;
import java.util.List;

public interface ICsvFileGenerator {

  void generateCsvFile(List<IResult> results, String outputDir) throws IOException;

}
