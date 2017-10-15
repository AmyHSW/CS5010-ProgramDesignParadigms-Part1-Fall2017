package edu.neu.ccs.cs5010.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>Template</code> class generates emails by replacing the placeholders in email
 * template. The information of placeholders is given by an evaluator.
 *
 * @author Shuwan Huang
 */
public class Template {

    private static final String REGEX = "\\[\\[[^\\]]*\\]\\]"; // regular expression to find the placeholders
    private final String template;

    /**
     * Parses the template text file to a single string.
     * @param templateFileName a filename that holds the email template
     */
    public Template(String templateFileName) {
        template = convertToString(templateFileName);
    }

    // parses the template file to a single string.
    private String convertToString(String templateFileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(templateFileName))) {
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
     * Replaces the placeholders by information provided by the Evaluator object.
     * @param evaluator an Evaluator
     * @return a string that contains the email to the passenger
     */
    public String generateEmail(Evaluator evaluator) {
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            String placeholder = matcher.group();
            replacePlaceholder(matcher, sb, placeholder, evaluator);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    // replaces the placeholder if it matches the info in evaluator,
    // otherwise throws an InvalidPlaceholderException.
    private void replacePlaceholder(Matcher matcher, StringBuffer sb, String placeholder, Evaluator evaluator) {
        String header = placeholder.substring(2, placeholder.length() - 2);
        if (evaluator.getValue(header) != null) {
            matcher.appendReplacement(sb, evaluator.getValue(header));
        } else {
            throw new InvalidPlaceholderException("Cannot determine placeholder information: " + placeholder);
        }
    }

}
