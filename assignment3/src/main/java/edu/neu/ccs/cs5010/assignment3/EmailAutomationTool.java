package edu.neu.ccs.cs5010.assignment3;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>EmailAutomationTool</code> class provides a tool to automate the process of email generation.
 * The arguments that passed to the constructor should be in a legal format that contains email template filename,
 * output direction, csv filename and the event.
 * <p></p>
 * This class uses a MemberDatabase to store the member information given in the csv file, and uses a EmailGenerator
 * to generate emails for each member given the email template.
 * <p></p>
 * When the <code>startEmailAutomation()</code> method is called, emails will be generated per member and saved in
 * output directory. If from-email and password are provided by user, the emails will be sent right away.
 *
 * @author Shuwan Huang
 */
public class EmailAutomationTool implements IEmailAutomationTool {

    private final MemberDatabase memberDB;
    private final EmailGenerator emailGenerator;
    private final File outputDir; // the folder to save the emails
    private final String fromEmail; // the email address from which to send the emails
    private final String password; // the password to the email address

    /**
     * Constructs a new EmailAutomationTool object with the given arguments. Checks that arguments is in
     * legal format, if not, throws an InvalidInputFormatException.
     * Initializes the member database and email generator, as well as the output directory.
     *
     * @param args the String array that contains information of email template filename, output direction,
     *             csv filename and the event, with optional information of from-email and password
     */
    public EmailAutomationTool(String[] args) {
        ArgumentsParser argumentParser = new ArgumentsParser(args);
        if (!argumentParser.isLegalFormat()) {
            System.out.println(argumentParser.getErrorMessage());
            throw new InvalidInputFormatException("arguments are in wrong format.");
        }
        memberDB = new MemberDatabase(argumentParser.getCsvFile());
        emailGenerator = new EmailGenerator(argumentParser.getEmailTemplate(), argumentParser.getFlightInfo());
        outputDir = new File(argumentParser.getOutputDir());
        fromEmail = argumentParser.getFromEmail();
        password = argumentParser.getPassword();
    }

    /**
     * Starts the email automation process. For each member, replaces the placeholders in email template with
     * member information, then saves the new email to directed output.
     * <p></p>
     * If user has provided the from-email and corresponding password, sends the email to member right away.
     *
     * @throws MessagingException if the email address or password does not work when sending emails
     */
    @Override
    public void startEmailAutomation() throws MessagingException {
        outputDir.mkdir();
        int i = 1;
        for (IMember member : memberDB.getMemberList()) {
            String email = emailGenerator.getEmail(member);
            File emailText = new File(outputDir, "email" + i++ +".txt");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(emailText))) {
                bw.write(email);
            } catch (IOException ioe) {
                System.out.println("Something went wrong!: " + ioe.getMessage());
                ioe.printStackTrace();
            }
            if (fromEmail != null && password != null) {
                sendEmail(email, member.getAttribute("email"));
            }
        }
    }

    // sends the email to the member
    private void sendEmail(String email, String toEmail) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        Pattern pattern = Pattern.compile("Dear");
        Matcher matcher = pattern.matcher(email);
        String text = matcher.find() ? email.substring(matcher.start()) : "";

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject("Please accept our apologies for the arrival of your flight");
        message.setText(text);
        Transport.send(message);
    }

    /**
     * Reads in the command-line arguments from user. Creates a new EmailAutomationTool and starts
     * email automation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws MessagingException {
        IEmailAutomationTool emailAutomationTool = new EmailAutomationTool(args);
        emailAutomationTool.startEmailAutomation();
    }

}
