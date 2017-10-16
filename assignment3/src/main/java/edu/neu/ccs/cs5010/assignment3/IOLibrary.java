package edu.neu.ccs.cs5010.assignment3;

import java.io.*;

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
  public static String convertFileToString(String file) {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        sb.append(line);
        sb.append(System.getProperty("line.separator"));
      }
    } catch (IOException ioe) {
      System.out.println("Something went wrong!: " + ioe.getMessage());
      ioe.printStackTrace();
    }
    return sb.toString();
  }

  /**
   * Writes the string to the file.
   * @param file the file where the string is written.
   * @param str a string
   */
  public static void writeStringToFile(File file, String str) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
      bw.write(str);
    } catch (IOException ioe) {
      System.out.println("Something went wrong!: " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

}
