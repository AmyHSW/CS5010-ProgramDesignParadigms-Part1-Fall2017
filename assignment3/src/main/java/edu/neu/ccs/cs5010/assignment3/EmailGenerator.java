package edu.neu.ccs.cs5010.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>EmailGenerator</code> class generates emails by replacing the placeholders in email
 * template. The member information is given by the Member object.
 *
 * @author Shuwan Huang
 */
public class EmailGenerator implements IEmailGenerator {

    private static final String REGEX = "\\[\\[[^\\]]*\\]\\]"; // regular expression to find the placeholders
    private final Map<String, String> flightInfo;
    private final String template;

    /**
     * Parses the template text file to a single string. Initializes flightInfo with the given map. Puts
     * the current date to the flightInfo.
     * @param templateFileName a filename that holds the email template
     * @param flightInfo a map that contains the flight information
     */
    public EmailGenerator(String templateFileName, Map<String, String> flightInfo) {
        template = parseTemplate(templateFileName);

        this.flightInfo = flightInfo;
        flightInfo.put("Date", (new SimpleDateFormat()).format(Calendar.getInstance().getTime()));
    }

    // parses the template file to a single string.
    private String parseTemplate(String templateFileName) {
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
     * Replaces the placeholders by member information provided by the Member object, or the flight
     * information stored in the flightInfo map.
     * @param member IMember object
     * @return a string that contains the email to the member
     */
    @Override
    public String getEmail(IMember member) {
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            String placeholder = matcher.group();
            replacePlaceholder(matcher, sb, placeholder, member);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    // replaces the placeholder if it matches the info provided in Member object or flightInfo map,
    // otherwise throws an InvalidPlaceholderException.
    private void replacePlaceholder(Matcher matcher, StringBuffer sb, String placeholder, IMember member) {
        String attribute = placeholder.substring(2, placeholder.length() - 2);
        if (flightInfo.containsKey(attribute)) {
            matcher.appendReplacement(sb, flightInfo.get(attribute));
        } else if (member.hasAttribute(attribute)) {
            matcher.appendReplacement(sb, member.getAttribute(attribute));
        } else {
            throw new InvalidPlaceholderException("Cannot determine placeholder information: " + placeholder);
        }
    }

}
