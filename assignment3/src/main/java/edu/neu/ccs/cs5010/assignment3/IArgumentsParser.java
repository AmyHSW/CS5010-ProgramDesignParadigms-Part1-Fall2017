package edu.neu.ccs.cs5010.assignment3;

import java.util.Map;

/**
 * The <code>IArgumentsParser</code> interface represents an arguments parser.
 *
 * @author Shuwan Huang
 */
public interface IArgumentsParser {

    /**
     * Returns true if arguments are in legal format and false otherwise.
     * @return true if arguments are in legal format and false otherwise.
     */
    boolean isLegalFormat();

    /**
     * Returns the error message if arguments are in illegal format; null if arguments are in
     * legal format.
     * @return the error message if arguments are in illegal format; null if arguments are in
     * legal format.
     */
    String getErrorMessage();

    /**
     * Returns a filename that holds the email template; null if arguments are not in legal format.
     * @return a filename that holds the email template; null if arguments are not in legal format.
     */
    String getEmailTemplate();

    /**
     * Returns the name of a folder that will store all output; null if arguments are not in legal format.
     * @return the name of a folder that will store all output; null if arguments are not in legal format.
     */
    String getOutputDir();

    /**
     * Returns the name of csv file; ; null if arguments are not in legal format
     * @return the name of csv file; ; null if arguments are not in legal format
     */
    String getCsvFile();

    /**
     * Returns a map that contains flight information parsed from arguments; null if arguments
     * are not in legal format.
     * @return a map that contains flight information parsed from arguments; null if arguments
     * are not in legal format.
     */
    Map<String, String> getFlightInfo();
}
