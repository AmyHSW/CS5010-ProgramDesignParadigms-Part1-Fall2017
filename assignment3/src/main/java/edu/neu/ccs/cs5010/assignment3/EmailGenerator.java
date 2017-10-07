package edu.neu.ccs.cs5010.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailGenerator implements IEmailGenerator {

    private static final String REGEX = "\\[\\[[^\\]]*\\]\\]";
    private final Map<String, String> flightInfo;
    private final String template;

    public EmailGenerator(String templateFileName, Map<String, String> flightInfo) {
        template = parseTemplate(templateFileName);

        this.flightInfo = flightInfo;
        flightInfo.put("Date", (new SimpleDateFormat()).format(Calendar.getInstance().getTime()));
    }

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
