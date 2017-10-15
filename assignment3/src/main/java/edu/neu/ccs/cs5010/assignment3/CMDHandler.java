package edu.neu.ccs.cs5010.assignment3;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>ArgumentsParser</code> class validates the format of arguments and parses the arguments
 * into information of email template, output directory, csv file and event. If the arguments also contain
 * from-email and password, parses them as well.
 * <p></p>
 * It provides a method <code>isLegalFormat</code> to check if arguments are in legal format , and getters to
 * obtain the above information.
 *
 * @author Shuwan Huang
 */
public class CMDHandler {

    private static final String EMAIL_TEMPLATE = "--email-template";
    private static final String OUTPUT_DIR = "--output-dir";
    private static final String CSV_FILE = "--csv-file";
    private static final String EVENT = "--event";
    private static final String MODE = "--mode";
    private static final String FROM_EMAIL = "--from-email";
    private static final String PASSWORD = "--password";
    private static final String USAGE_MSG =
            "Usage: \n\n"
            + "--email-template <file>      accepts a filename that holds the email template\n\n"
            + "--output-dir <path>          accepts the name of a folder, all output is placed\n"
            + "                             in this folder\n\n"
            + "--csv-file <path>            accepts the name of the csv file to process, in\n"
            + "                             a following format\n"
            + "                             Flight<id>From<departure-city>To<destination-city>.csv\n\n"
            + "--event <details>            specifies if the delay refers to departure/arrival\n\n"
            + "--from-email <email>         (optional) accepts an email address from which the emails\n"
            + "                             will be sent\n\n"
            + "--password <password>        (optional) accepts the password to the email address provided\n\n"
            + "--mode <mode>                (optional) accepts the mode of email automation, 1 (default mode)\n"
            + "                             is to save emails in a folder, 2 is to send emails to passengers\n\n"
            + "For example:\n\n"
            + "--email-template  email-template.txt  --output-dir  emails  --csv-file\n"
            + "Flight363FromSeattleToBoston.csv  --event  arrival\n";


    private static final int DEFAULT_MODE = 1;

    private final Map<String, String> userInput; // stores the user-input arguments
    private final StringBuilder errorMsg; // stores error message

    /**
     * Constructs a new ArgumentParser with the given String array. Initializes the userInput as a HashMap
     * that stores user input information with attributes as keys. Initializes the errorMsg as an empty
     * StringBuilder, and the flightInfo as an empty HashMap.
     *
     * @param args a String array
     */
    public CMDHandler(String[] args) {
        userInput = new HashMap<>();
        userInput.put(EMAIL_TEMPLATE, null);
        userInput.put(OUTPUT_DIR, null);
        userInput.put(CSV_FILE, null);
        userInput.put(EVENT, null);
        userInput.put(MODE, null);
        userInput.put(FROM_EMAIL, null);
        userInput.put(PASSWORD, null);

        errorMsg = new StringBuilder();

        parseArgs(args);
    }

    // parses the arguments and puts them in map if arguments are in legal format.
    private void parseArgs(String[] args) {
        int i = 0;

        while (i < args.length) {

            String arg = args[i++];

            if (!isHeader(arg)) continue;

            if (!isRecognizable(arg)) {
                errorMsg.append(arg);
                errorMsg.append(" cannot be recognized\n");
                continue;
            }

            if (!isArgument(args, i)) {
                errorMsg.append(arg);
                errorMsg.append(" was given without providing appropriate argument\n");
                continue;
            }

            if (validate(arg, args[i])) {
                userInput.put(arg, args[i++]);
            } else {
                i++;
            }
        }
    }

    private boolean isHeader(String arg) {
        return arg.startsWith("--");
    }

    private boolean isRecognizable(String arg) {
        return userInput.containsKey(arg);
    }

    private boolean isArgument(String[] args, int i) {
        return i < args.length && !args[i].startsWith("--");
    }

    private boolean validate(String header, String arg) {
        if (header.equals(EMAIL_TEMPLATE)) {
            return validateEmailTemplate(arg);
        }
        if (header.equals(CSV_FILE)) {
            return validateCsvFile(arg);
        }
        if (header.equals(EVENT)) {
            return validateEvent(arg);
        }
        return true;
    }

    // checks if the provided email template is a text file, if yes, puts it in the userInput map.
    private boolean validateEmailTemplate(String template) {
        if (template.endsWith(".txt")) {
            return true;
        } else {
            errorMsg.append(EMAIL_TEMPLATE);
            errorMsg.append(" argument does not provide a text filename\n");
            return false;
        }
    }

    // checks if the csv filename contains necessary information, if yes, puts it in the userInput map.
    // obtains flight id, departure city and destination city from the filename, and puts in flightInfo map.
    // assumes the flight id is only numbers, and city names are only letters.
    private boolean validateCsvFile(String file) {
        if (!file.endsWith(".csv")) {
            errorMsg.append(CSV_FILE);
            errorMsg.append(" argument is not a csv file\n");
            return false;
        }
        Pattern pattern = Pattern.compile("Flight([0-9]+)From([A-Z][a-z]+)To([A-Z][a-z]+).csv");
        Matcher matcher = pattern.matcher(file);
        if (matcher.matches()) {
            return true;
        } else {
            errorMsg.append(CSV_FILE);
            errorMsg.append(" argument does not contain flight number or departure-city or destination-city\n");
            return false;
        }
    }

    // checks if the provided event is either arrival or departure, if yes, puts it in userInput map.
    private boolean validateEvent(String event) {
        if (event.equals("arrival") || event.equals("departure")) {
            return true;
        } else {
            errorMsg.append(EVENT);
            errorMsg.append(" argument does not contain a departure/arrival event\n");
            return false;
        }
    }

    /**
     * Returns true if the required information -- email template, output directory,
     * csv file and event detail -- are provided in legal format.
     * @return true if arguments are in legal format and false otherwise.
     */
    public boolean isLegalFormat() {
            return userInput.get(EMAIL_TEMPLATE) != null
                    && userInput.get(OUTPUT_DIR) != null
                    && userInput.get(CSV_FILE) != null
                    && userInput.get(EVENT) != null;
    }

    /**
     * Returns the error message if arguments are in illegal format; null if arguments are in
     * legal format.
     * @return the error message if arguments are in illegal format; null if arguments are in
     * legal format.
     */
    public String getErrorMessage() {
        if (isLegalFormat()) return null;
        if (errorMsg.toString().equals("")) {
            errorMsg.append("Some required arguments are missing! Please see below an example of input\n");
        }
        return "Error:\n\n" + errorMsg.toString() + "\n" + USAGE_MSG;
    }

    /**
     * Returns a filename that holds the email template; null if arguments are not in legal format.
     * @return a filename that holds the email template; null if arguments are not in legal format.
     */
    public String getTemplate() {
        if (!isLegalFormat()) return null;
        return userInput.get(EMAIL_TEMPLATE);
    }

    /**
     * Returns the name of a folder that will store all output; null if arguments are not in legal format.
     * @return the name of a folder that will store all output; null if arguments are not in legal format.
     */
    public String getOutputDir() {
        if (!isLegalFormat()) return null;
        return userInput.get(OUTPUT_DIR);
    }

    /**
     * Returns the name of csv file; null if arguments are not in legal format
     * @return the name of csv file; null if arguments are not in legal format
     */
    public String getCsvFile() {
        if (!isLegalFormat()) return null;
        return userInput.get(CSV_FILE);
    }

    public String getEvent() {
        if (!isLegalFormat()) return null;
        return userInput.get(EVENT);
    }

    public int getMode() {
        if (userInput.get(MODE) == null) return DEFAULT_MODE;
        return Integer.parseInt(userInput.get(MODE));
    }

    /**
     * Returns the email address from which to send the emails; null if arguments are not in legal format or
     * if email address is not provided.
     * @return the email address from which to send the emails; null if arguments are not in legal format or
     * if email address is not provided.
     */
    public String getFromEmail() {
        if (!isLegalFormat()) return null;
        return userInput.get(FROM_EMAIL);
    }

    /**
     * Returns the password to the email address; null if arguments are not in legal format or if
     * email address and password are not provided.
     * @return the password to the email address; null if arguments are not in legal format or if
     * email address and password are not provided.
     */
    public String getPassword() {
        if (!isLegalFormat()) return null;
        return userInput.get(PASSWORD);
    }

}