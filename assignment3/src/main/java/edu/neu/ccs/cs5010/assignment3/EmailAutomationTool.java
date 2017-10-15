package edu.neu.ccs.cs5010.assignment3;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class EmailAutomationTool {

    private static final int SAVE_EMAILS = 1;
    private static final int SEND_EMAILS = 2;

    private final CMDHandler cmdHandler;
    private int mode;

    /**
     * Constructs a new EmailAutomationTool object with the given arguments. Checks that arguments is in
     * legal format, if not, throws an InvalidInputFormatException.
     * Initializes the member database and email generator, as well as the output directory.
     *
     * @param args the String array that contains information of email template filename, output direction,
     *             csv filename and the event, with optional information of from-email and password
     */
    public EmailAutomationTool(String[] args) throws Exception {
        cmdHandler = new CMDHandler(args);
        init();
        startEmailGeneration();
    }

    private void init() {
        if (!cmdHandler.isLegalFormat()) {
            System.out.println(cmdHandler.getErrorMessage());
            throw new InvalidInputFormatException("arguments are in wrong format.");
        }
        mode = cmdHandler.getMode();
    }

    private void startEmailGeneration() throws Exception {
        List<String> emails = new ArrayList<>();
        EvaluatorContainer ec = new EvaluatorContainer(new DateEvaluator());
        Evaluator constEvaluator = new ConstEvaluator(new FlightInfo(cmdHandler));
        ec.add(constEvaluator);
        Template t = new Template(cmdHandler.getTemplate());
        PassengerInfo psInfo = new PassengerInfo(cmdHandler.getCsvFile());
        int i = 1;
        while (psInfo.hasNextPassenger()) {
            ec.add(new PassengerEvaluator(psInfo.nextPassenger()));
            String email = t.generateEmail(ec);
            emails.add(email);
        }
        if (mode == SAVE_EMAILS) saveEmails(emails);
        else if (mode == SEND_EMAILS) sendEmails(emails);
    }

    // saves email to the desired output directory
    private void saveEmails(List<String> emails) {
        File outputDir = new File(cmdHandler.getOutputDir());
        outputDir.mkdir();
        for (int i = 0; i < emails.size(); i++) {
            File text = new File(outputDir, "email" + (i + 1) + ".txt");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(text))) {
                bw.write(emails.get(i));
            } catch (IOException ioe) {
                System.out.println("Something went wrong!: " + ioe.getMessage());
                ioe.printStackTrace();
            }
        }
    }

    /**
     * Starts the email automation process. For each member, replaces the placeholders in email template with
     * member information, then saves the new email to directed output.
     * <p></p>
     * If user has provided the from-email and corresponding password, sends the email to member right away.
     *
     * @throws MessagingException if the email address or password does not work when sending emails
     */


    // sends the email to the member
    private void sendEmails(List<String> emails) throws MessagingException {
        String fromEmail = cmdHandler.getFromEmail();
        String password = cmdHandler.getPassword();

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

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setSubject("Please accept our apologies for the arrival of your flight");

        for (String email : emails) {
            Pattern pattern = Pattern.compile("Dear");
            Matcher matcher = pattern.matcher(email);
            String text = matcher.find() ? email.substring(matcher.start()) : "";

            pattern = Pattern.compile("To: (.*?)\nSubject");
            matcher = pattern.matcher(email);
            String toEmail = matcher.find() ? matcher.group(1) : "";

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setText(text);
            Transport.send(message);
        }
    }

    /**
     * Reads in the command-line arguments from user. Creates a new EmailAutomationTool and starts
     * email automation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws Exception {
        EmailAutomationTool emailAutomationTool = new EmailAutomationTool(args);
    }

}
