package edu.neu.ccs.cs5010.assignment3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>Email</code> class represents an email.
 * The parameter to constructor is a string of content, which contains the text of this email and
 * the to-email address.
 *
 * @author Shuwan Huang
 */
public class Email implements IEmail {

  private final String content;
  private String text;
  private String toEmail;

  /**
   * Constructs a new Email with a string of content.
   * @param content a string
   */
  public Email(String content) {
    this.content = content;
    parseContent(content);
  }

  // parses the content string to find the main text and to-email address
  private void parseContent(String content) {
    Pattern pattern = Pattern.compile("Dear");
    Matcher matcher = pattern.matcher(content);
    if (matcher.find()) text = content.substring(matcher.start());

    pattern = Pattern.compile("To: (.*?)\nSubject");
    matcher = pattern.matcher(content);
    if (matcher.find()) toEmail = matcher.group(1);
  }

  /**
   * Returns the main text of this email.
   * @return the main text of this email.
   */
  @Override
  public String getText() {
    return text;
  }

  /**
   * Returns the email address to which this email will be sent.
   * @return the email address to which this email will be sent.
   */
  @Override
  public String getToEmail() {
    return toEmail;
  }

  /**
   * Returns the entire content of this email.
   * @return the entire content of this email.
   */
  @Override
  public String toString() {
    return content;
  }

}
