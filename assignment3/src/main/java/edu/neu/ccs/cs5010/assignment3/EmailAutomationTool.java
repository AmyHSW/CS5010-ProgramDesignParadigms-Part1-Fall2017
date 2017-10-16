package edu.neu.ccs.cs5010.assignment3;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The <code>EmailAutomationTool</code> class provides a tool to automate the process of
 * email generation. The command-line arguments are parsed by a CMDHandler.
 * An InvalidInputFormatException will be thrown if the arguments are not in legal format.
 * A legal format should contain email template filename, output direction, csv filename
 * and the event.
 * <p>
 * This class uses a PassengerInfo to store all passenger information given in the csv file.
 * A Template is used to find all placeholders in original email template, and replace
 * the placeholders with the info provided by a EvaluatorContainer. The EvaluatorContainer
 * contains all sources of info, including date, flight info, and passenger info. It helps
 * the Template to replace the placeholders and generate new emails.
 * <p>
 * The emails will be generated for each passenger. If user selects the "save emails" mode,
 * then emails will be saved in the desired output directory. If user selects "send emails"
 * mode, then emails will be sent to the passengers.
 *
 * @author Shuwan Huang
 */
public class EmailAutomationTool {

  private static final int SAVE_EMAILS = 1; // the mode of "save emails"
  private static final int SEND_EMAILS = 2; // the mode of "send emails"
  private static final String SOURCE_CITY_PLACEHOLDER = "departure-city";
  private static final String DESTIN_CITY_PLACEHOLDER = "destination-city";
  private static final String EVENT_PLACEHOLDER = "event";

  private final ICMDHandler cmdHandler;

  /**
   * Constructs a new EmailAutomationTool object with a CMDHandler. Starts the
   * email generations.
   *
   * @param cmdHandler a CMDHandler
   */
  public EmailAutomationTool(ICMDHandler cmdHandler) throws Exception {
    this.cmdHandler = cmdHandler;
    startEmailGeneration();
  }

  // initializes the evaluator container by pushing all sources of evaluators.
  // For each passenger, generates a new email and stores email in a email list.
  // After all emails have been generated, either saves to a folder or sends to
  // passengers, depending on the mode that user selected.
  private void startEmailGeneration() throws Exception {
    ITemplate template = new Template(cmdHandler.getTemplate());
    IPassengerInfo psInfo = new PassengerInfo(cmdHandler.getCsvFile());
    IFlightInfo flightInfo = new FlightInfo(cmdHandler);

    IEvaluatorContainer ec = new EvaluatorContainer();
    ec.push(new DateEvaluator());
    ec.push(new KeyValueEvaluator(SOURCE_CITY_PLACEHOLDER, flightInfo.getSourceCity()));
    ec.push(new KeyValueEvaluator(DESTIN_CITY_PLACEHOLDER, flightInfo.getDestinationCity()));
    ec.push(new KeyValueEvaluator(EVENT_PLACEHOLDER, flightInfo.getEvent()));

    List<String> headers = psInfo.getHeaders();

    List<IEmail> emails = new ArrayList<>();
    int i = 1;
    while (psInfo.hasNextPassenger()) {
      ec.push(new KeyValueEvaluator(headers, psInfo.nextPassenger()));
      emails.add(template.toEmail(ec));
      ec.pop();
    }

    if (cmdHandler.getMode() == SAVE_EMAILS) saveEmails(emails);
    if (cmdHandler.getMode() == SEND_EMAILS) sendEmails(emails);
  }

  // saves emails to the desired output directory,
  // throws InvalidOutputDirException if the output directory already exists.
  private void saveEmails(List<IEmail> emails) throws IOException {
    File outputDir = new File(cmdHandler.getOutputDir());
    if (!outputDir.mkdir()) {
      throw new InvalidOutputDirException(outputDir + " already exists!");
    }
    for (int i = 0; i < emails.size(); i++) {
      File file = new File(outputDir, "email" + (i + 1) + ".txt");
      IOLibrary.writeStringToFile(file, emails.get(i).toString());
    }
  }

  // sends the emails to the passengers
  private void sendEmails(List<IEmail> emails) throws MessagingException {
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

    for (IEmail email : emails) {
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getToEmail()));
      message.setText(email.getText());
      Transport.send(message);
    }
  }

  /**
   * Reads in the command-line arguments from user. Creates a CMDHandler to check the
   * format of arguments. Prints to console the error message if the arguments are not
   * in legal format. Otherwise created a EmailAutomationTool with the CMDHandler object.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) throws Exception {
		ICMDHandler cmdHandler = new CMDHandler(args);
    if (!cmdHandler.isLegalFormat()) {
      System.out.println(cmdHandler.getErrorMessage());
      throw new InvalidInputFormatException("arguments are in wrong format.");
    }
    new EmailAutomationTool(cmdHandler);
  }

}
