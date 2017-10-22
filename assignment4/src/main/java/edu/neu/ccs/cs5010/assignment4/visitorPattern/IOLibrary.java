package edu.neu.ccs.cs5010.assignment4.visitorPattern;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * The <code>IOLibrary</code> provides two method to read and write files.
 *
 * @author Shuwan Huang
 */
public final class IOLibrary {

  /**
   * Reads the file and returns a string of content.
   * @param file the file name
   * @return a string of content
   */
  public static String convertFileToString(String file) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();

    InputStream inputStream = new FileInputStream(file);
    Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    BufferedReader bufferedReader = new BufferedReader(reader);
    String line;
    try {
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line);
        stringBuilder.append(System.getProperty("line.separator"));
      }
    } finally {
      bufferedReader.close();
    }

    return stringBuilder.toString();
  }

  /**
   * Writes the string to the file.
   * @param file the file where the string is written.
   * @param str a string
   */
  public static void writeStringToFile(File file, String str) throws IOException {
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
