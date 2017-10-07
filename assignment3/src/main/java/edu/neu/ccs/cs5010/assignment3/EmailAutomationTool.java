package edu.neu.ccs.cs5010.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The <code>EmailAutomationTool</code> class provides a tool to automate the process of email generation.
 * The arguments that passed to the constructor should be in a legal format that contains email template filename,
 * output direction, csv filename and the event.
 *
 * This class uses a MemberDatabase to store the member information given in the csv file, and uses a EmailGenerator
 * to generate emails for each member given the email template.
 *
 * When the <code>startEmailAutomation()</code> method is called, emails will be generated per member and saved in
 * output directory.
 *
 * @author Shuwan Huang
 */
public class EmailAutomationTool implements IEmailAutomationTool {

    private final MemberDatabase memberDB;
    private final EmailGenerator emailGenerator;
    private final File outputDir; // the folder to save the emails

    /**
     * Constructs a new EmailAutomationTool object with the given arguments. Checks that arguments is in
     * legal format, if not, throws an InvalidInputFormatException.
     * Initializes the member database and email generator, as well as the output directory.
     *
     * @param args the String array that contains information of email template filename, output direction,
     *             csv filename and the event
     */
    public EmailAutomationTool(String[] args) {
        ArgumentsParser argumentParser = new ArgumentsParser(args);
        if (!argumentParser.isLegalFormat()) {
            System.out.println(argumentParser.getErrorMessage());
            throw new InvalidInputFormatException("command-line arguments are in wrong format");
        }
        memberDB = new MemberDatabase(argumentParser.getCsvFile());
        emailGenerator = new EmailGenerator(argumentParser.getEmailTemplate(), argumentParser.getFlightInfo());
        outputDir = new File(argumentParser.getOutputDir());
    }

    /**
     * Starts the email automation process. For each member, replaces the placeholders in email template with
     * member information, then saves the new email to directed output.
     */
    @Override
    public void startEmailAutomation() {
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
        }
    }

    /**
     * Reads in the command-line arguments from user. Creates a new EmailAutomationTool and starts
     * email automation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        IEmailAutomationTool emailAutomationTool = new EmailAutomationTool(args);
        emailAutomationTool.startEmailAutomation();
    }

}
