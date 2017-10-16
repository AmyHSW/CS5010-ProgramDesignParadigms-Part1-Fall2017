package edu.neu.ccs.cs5010.assignment3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>Template</code> class generates emails by replacing the placeholders in email
 * template. The information of placeholders is given by an evaluator.
 *
 * @author Shuwan Huang
 */
public class Template implements ITemplate {

  private static final String REGEX = "\\[\\[([^\\]]*)\\]\\]"; // regex to find the placeholders
  private final String templateText;
  private final List<String> placeholders;

  /**
   * Constructs a Template object. Converts the email template text file to a string,
   * and finds placeholders in the email template.
   *
   * @param templateFileName a filename that holds the email template
   */
  public Template(String templateFileName) throws IOException {
    templateText = IOLibrary.convertFileToString(templateFileName);
    placeholders = findPlaceholders(templateText);
  }

  private List<String> findPlaceholders(String templateText) {
    List<String> placeholders = new ArrayList<>();
    Pattern pattern = Pattern.compile(REGEX);
    Matcher matcher = pattern.matcher(templateText);
    while (matcher.find()) {
      placeholders.add(matcher.group(1));
    }
    return placeholders;
  }

  /**
   * Replaces the placeholders by information provided by the Evaluator object.
   *
   * @param evaluator an Evaluator
   * @return an Email object that contains the email to the passenger
   */
  @Override
  public IEmail toEmail(Evaluator evaluator) {
    String emailContent = templateText;
    for (String placeholder : placeholders) {
      String value = evaluator.getValue(placeholder);
      if (value == null) {
        throw new InvalidPlaceholderException("Cannot determine placeholder information: "
                                              + placeholder);
      }
      emailContent = emailContent.replaceAll("\\[\\[" + placeholder + "\\]\\]", value);
    }
    return new Email(emailContent);
  }

}
