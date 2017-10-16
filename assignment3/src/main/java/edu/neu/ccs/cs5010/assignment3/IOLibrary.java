package edu.neu.ccs.cs5010.assignment3;

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
    StringBuilder sb = new StringBuilder();

    InputStream in = new FileInputStream(file);
    Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);
    String line;
    try {
      while ((line = br.readLine()) != null) {
        sb.append(line);
        sb.append(System.getProperty("line.separator"));
      }
    } finally {
      br.close();
    }

    return sb.toString();
  }

  /**
   * Writes the string to the file.
   * @param file the file where the string is written.
   * @param str a string
   */
  public static void writeStringToFile(File file, String str) throws IOException {
    OutputStream out = new FileOutputStream(file);
    Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
    BufferedWriter bw = new BufferedWriter(writer);
    try {
      bw.write(str);
    } finally {
      bw.flush();
      bw.close();
    }
  }

}
